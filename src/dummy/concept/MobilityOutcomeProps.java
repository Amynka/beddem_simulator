package dummy.concept;

import java.util.Map;

import framework.concept.OutcomeProps;

public class MobilityOutcomeProps implements OutcomeProps {
	private Map<String, Double> vehicleIDToDistance;
	private double price;
	private double totalTime;
	private double comfort;
	private double saving;
	private int pastFreq;
	private double influence;
	private boolean isLate;
	private double probability;
	private boolean climateAwareness;

	public MobilityOutcomeProps(boolean isLate, Map<String, Double> vehicleIDToDistance, double price, double totalTime,
			double comfort, double saving, int pastFreq, double influence, boolean climateAwareness,
			double probability) {
		this.isLate = isLate;
		this.vehicleIDToDistance = vehicleIDToDistance;
		this.price = price;
		this.totalTime = totalTime;
		this.comfort = comfort;
		this.saving = saving;
		this.pastFreq = pastFreq;
		this.influence = influence;
		this.probability = probability;
		this.climateAwareness = climateAwareness;
	}

	public boolean isLate() {
		return this.isLate;
	}

	public double getVehicleIDToDistance(String vehicleID) {
		Double result = this.vehicleIDToDistance.get(vehicleID);
		if (result == null) {
			return 0;
		}
		return result;
	}

	public double getPrice() {
		return price;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public double getComfort() {
		return comfort;
	}

	public double getSaving() {
		return saving;
	}

	public int getPastFreq() {
		return pastFreq;
	}

	public double getInfluence() {
		return influence;
	}

	public double getProbability() {
		return this.probability;
	}

	public boolean hasClimateAwareness() {
		return this.climateAwareness;
	}

}
