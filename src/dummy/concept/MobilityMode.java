package dummy.concept;

import dummy.simulator.ContextManager;
import dummy.simulator.GlobalVars;

public class MobilityMode extends Resource {

	private String serviceType;
	private String type;
	private String motorType;
	private double fuelConsumeLv;
	private double shortDisSpeed;
	private double normalDisSpeed;
	private double longDisSpeed;
	private int nSeats;
	private double CO2perKm;
	private double nonCO2perKm;
	private double otherProductPerKm;
	private double comfortRate;

	public MobilityMode(String id, String type, String motorType, double fuelConsumeLv, double CO2perKm,
			double nonCO2perKm, double otherProductPerKm, double shortDisSpeed, double normalDisSpeed,
			double longDisSpeed, int nSeats, double comfortRate) {
		super(id);
		this.serviceType = type;
		if (type.contains("car") || type.contains("SUV")) {
			this.serviceType = "Car";
		}
		if (type.contains("quadricycles")) {
			this.serviceType = type;
		}
		if (type.contains("Bus")) {
			this.serviceType = "Bus";
		}
		if (type.contains("Tram")) {
			this.serviceType = "Tram";
		}
		if (type.contains("Train")) {
			this.serviceType = "Train";
		}
		if (type.contains("Walking")) {
			this.serviceType = "Walking";
		}
		if (type.contains("Bike")) {
			this.serviceType = "Biking";
		}

		this.type = type;
		this.motorType = motorType;
		this.fuelConsumeLv = fuelConsumeLv;
		this.CO2perKm = CO2perKm;
		this.nonCO2perKm = nonCO2perKm;
		this.otherProductPerKm = otherProductPerKm;
		this.shortDisSpeed = shortDisSpeed;
		this.normalDisSpeed = normalDisSpeed;
		this.longDisSpeed = longDisSpeed;
		this.nSeats = nSeats;
		this.comfortRate = comfortRate;
	}

	@Override
	public boolean isExpired() {
		// TODO: Implement this!
		return false;
	}

	/**
	 * Get the number of available seats.
	 * 
	 * @return The number of avalable seats.
	 */
	public int getAvalaibleSeats() {
		return this.nSeats;
	}

	/**
	 * Get the comfort rate.
	 * 
	 * @return The comfort rate.
	 */
	public double getComfortRate() {
		return this.comfortRate;
	}

	/**
	 * Get the type of the motor of transportation mode.
	 * 
	 * @return The type of the motor of transportation mode.
	 */
	public String getMotorType() {
		return this.motorType;
	}

	/**
	 * Get the type of the transportation mode (i.e car, bike, walk, etc.)
	 * 
	 * @return The type of the transportation mode.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Get the amount of CO2 products per km for this mode.
	 * 
	 * @return The amount of CO2 products per km for this mode.
	 */
	public double getCO2perKm() {
		return this.CO2perKm;
	}

	/**
	 * Get the amount of non-CO2 products per km for this mode.
	 * 
	 * @return The amount of non-CO2 products per km for this mode.
	 */
	public double getNonCO2perKm() {
		return this.nonCO2perKm;
	}

	/**
	 * Get the amount of other products per km for this mode.
	 * 
	 * @return The amount of other products per km for this mode.
	 */
	public double getOtherProductsperKm() {
		return this.otherProductPerKm;
	}

	/**
	 * Get the time required for the agent to use the vehicle.
	 * 
	 * @param distanceByVehicle Distance to be covered by the vehicle.
	 * @param disType           The type of distance to be covered by the vehicle.
	 * @return The time required for the agent to use the vehicle.
	 */
	public double getDuration(double distanceByVehicle, String disType) {
		double speed = this.longDisSpeed;
		switch (disType) {
		case "S":
			speed = this.shortDisSpeed;
			break;
		case "N":
			speed = this.normalDisSpeed;
			break;
		}
		return distanceByVehicle / speed;
	}

	public double getCost(double distanceByVehicle) {
		return distanceByVehicle * this.fuelConsumeLv
				* GlobalVars.GLOBAL_FUEL_PRICE.getPrice(ContextManager.getCheckPointNum(), this.type, this.motorType);
	}

	public void changeComfortRate(double newComfortRate) {
		this.comfortRate = newComfortRate;
	}

	public double getFuelConsumption() {
		return this.fuelConsumeLv;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (other instanceof MobilityMode) {
			MobilityMode otherMode = (MobilityMode) other;
			return this.id.equals(otherMode.id);
		}
		return false;

	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

}
