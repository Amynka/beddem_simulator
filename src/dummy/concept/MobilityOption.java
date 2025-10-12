package dummy.concept;

import java.util.Iterator;
import java.util.List;

import framework.concept.Option;
import framework.concept.OutcomeProps;

public class MobilityOption implements Option {

	private String service;
	private MobilityMode transportMode;

	private List<OutcomeProps> outcomePropsList;

	/**
	 * @param outcomePropsList The list of all outcomes' properties, which can be
	 *                         used in the evaluation of decision-making. (To be
	 *                         implemented)
	 */

	/**
	 * @return An iterator for all outcomes' properties.
	 */
	public Iterator<OutcomeProps> getOutcomePropsIter() {
		return this.outcomePropsList.iterator();
	}

	public OutcomeProps getOutcomeProps(int outcomeNum) {
		return this.outcomePropsList.get(outcomeNum);
	}

	public int getOutcomePropsSize() {
		return this.outcomePropsList.size();
	}

	public MobilityOption(List<OutcomeProps> mobilityOutPropsList, String service, MobilityMode transportMode) {
		this.service = service;
		this.transportMode = transportMode;
		this.outcomePropsList = mobilityOutPropsList;
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
			MobilityOption otherOpt = (MobilityOption) other;
			return this.transportMode.getID().equals(otherOpt.transportMode.getID());
		}
		return false;

	}

	@Override
	public int hashCode() {
		return this.transportMode.getID().hashCode();
	}

}
