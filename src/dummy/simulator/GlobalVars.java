package dummy.simulator;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import dummy.database.CSVReader;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;

/**
 * Placeholder of all constants for the simulation.
 * 
 * @author khoa_nguyen
 *
 */
public abstract class GlobalVars {

	// These are strings that match entries in the
	// beddem_simulator.properties file.
	public static final String CSVDataDirectory = "CSVDataDirectory";
	public static final String ResultDirectory = "ResultDirectory";
	public static final String AgentCSVfile = "AgentCSVfile";
	public static final String LocationCSVfile = "LocationCSVfile";
	public static final String ScheduleCSVfile = "ScheduleCSVfile";
	public static final String VehicleCSVfile = "VehicleCSVfile";
	public static final String ResourceCSVfile = "ResourceCSVfile";
	public static final String CorrespondenceCSVfile = "CorrespondenceCSVfile";
	public static final String EconomyCSVfile = "EconomyCSVfile";
	public static final String PolicyCSVfile = "PolicyCSVfile";
	public static final String RankingMatrixCSVfile = "RankingMatrixCSVfile";
	public static final String AgentWeightCSVfile = "AgentWeightCSVfile";
	public static final String AgentSociogramCSVfile = "AgentSociogramCSVfile";

	public static final String AgentTable = "AgentTable";
	public static final String CorrespondenceTable = "CorrespondenceTable";
	public static final String EconomyTable = "EconomyTable";
	public static final String ResourcesTable = "ResourcesTable";
	public static final String ScheduleTable = "ScheduleTable";
	public static final String WeightsTable = "WeightsTable";
	public static final String TransportationTable = "TransportationTable";
	public static final String TypeofvehiclesTable = "TypeofvehiclesTable";
	public static final String Sociogram = "Sociogram";

	// Names of contexts and projections. These names must match those in the
	// parameters.xml file so that they can be displayed properly in the GUI.
	public static final class CONTEXT_NAMES {

		public static final String MAIN_CONTEXT = "maincontext";
		public static final String MAIN_GEOGRAPHY = "MainGeography";

		public static final String LOCATION_CONTEXT = "LocationContext";
		public static final String LOCATION_GEOGRAPHY = "LocationGeography";

		public static final String AGENT_CONTEXT = "AgentContext";
		public static final String AGENT_GEOGRAPHY = "AgentGeography";

	}

	/**
	 * Parameters that control the simulation time and step
	 * 
	 * @author khoa_nguyen
	 *
	 */
	public static final class SIMULATION_PARAMS {
		private static Parameters params = RunEnvironment.getInstance().getParameters();

		// 1 checkpoint, 1 period == one day of the schedule
		public static final int CHECKPOINTS_IN_SIMULATE = (Integer) params.getValue("checkpoints_in_simulate");
		public static final int PERIODS_TO_NEXT_CHECKPOINT = (Integer) params.getValue("periods_to_checkpoint");
		public static final int AGENT_PROBABILISTIC_DECISION = (Integer) params
				.getValue("agent_made_probabilistic_decision");
		public static final double TIME_STEPS_IN_PERIOD = 24;
		public static final double TIME_OF_EACH_SCHEDULE = 24;
		public static final int NUMBER_OF_MODES = 6;
		public static final double MAX_WEIGHT = 5.0;
		// File path for the definition of input
		// files"./data/beddem_simulator.properties";
		public static final String PropertiesFile = "./data/" + params.getValueAsString("properties_file_name");

		public static int getPeriodToNextCheckNum() {
			Parameters params = RunEnvironment.getInstance().getParameters();
			return (Integer) params.getValue("periods_to_checkpoint");
		}
	}

	public static final class AGENT_DECISION_PARAMS {
		// public static final double WEIGHT_OF_HABITS = 0.5;
		// public static final double WEIGHT_OF_INTENTION = 0.5;
		// public static final int OPTIONS_CONSIDERED = 10;
		private static Random rand = new Random();
		public static final int CLIMATE_AWARE_POINTS = 1 + rand.nextInt(5);

		public static final double ENVIRONMENTAL_RANKING_VALUE_OF_MOTOR_NW = 1.0;
		public static final double ENVIRONMENTAL_RANKING_VALUE_OF_MOTOR_E = 2.5;
		public static final double ENVIRONMENTAL_RANKING_VALUE_OF_MOTOR_G = 5.0;

		public static final double WORK_SPACE_RANKING_VALUE_OF_RAIL_BUS_TRAM = 1.0;
		public static final double WORK_SPACE_RANKING_VALUE_OF_OTHER_MODE = 5.0;
	}

	public final static class GLOBAL_FUEL_PRICE {
		private static double priceOfG;
		private static double priceOfD;
		private static double priceOfE;
		private static double priceOfH;
		private static double extraPublic;
		private static double extraEV;
		private static double extraFossil;

		protected static void updatePrice(CSVReader reader) throws IOException {
			List<Double> newPrices = reader.createPrices();
			priceOfG = newPrices.get(0);
			priceOfD = newPrices.get(1);
			priceOfE = newPrices.get(2);
			priceOfH = newPrices.get(3);
			extraPublic = newPrices.get(4);
			extraEV = newPrices.get(5);
			extraFossil = newPrices.get(6);
		}

		public static double getPrice(int checkpoint, String typeOfService, String motorType) {

			if (typeOfService.equals("Bus") || typeOfService.equals("Tram") || typeOfService.equals("Train")) {
				return priceOfE + extraPublic;
			}
			if (motorType.contains("GSL")) {
				return priceOfG + extraFossil;
			}
			if (motorType.contains("DSL")) {
				return priceOfD + extraFossil;
			}
			if (motorType.contains("ELC")) {
				return priceOfE + extraEV;
			}
			if (motorType.contains("H20")) {
				return priceOfH;
			}
			return priceOfG + extraFossil;
		}
	}

}
