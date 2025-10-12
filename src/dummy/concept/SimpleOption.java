package dummy.concept;

import java.util.Map;

import framework.concept.Option;

public class SimpleOption implements Option {

	protected Map<String, Double> propertyToValueMap;

	private String service;
	private MobilityMode transportMode;

	public SimpleOption(Map<String, Double> propertyToValueMap, String service, MobilityMode transportMode) {
		this.service = service;
		this.transportMode = transportMode;
		this.propertyToValueMap = propertyToValueMap;
	}

	public double getPropertyValue(String propertyID) {
		Double cost = propertyToValueMap.get(propertyID);
		if (cost == null) {
			return 0;
		}
		return cost;
	}

	public String getService() {
		return this.service;
	}

	public MobilityMode getMainVehicle() {
		return this.transportMode;
	}

	@Override
	public String toString() {
		return "service :" + this.service + " main vehicle: " + this.transportMode.getID();

	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (other instanceof Option) {
			SimpleOption otherOpt = (SimpleOption) other;
			return this.transportMode.getID().equals(otherOpt.transportMode.getID());
		}
		return false;

	}

	@Override
	public int hashCode() {
		return this.service.hashCode() + this.transportMode.getID().hashCode();
	}

}
