package dummy.agent;

import org.apache.log4j.Logger;

import framework.agent.core.DecisionComponent;
import framework.agent.reasoning.Determinant;
import framework.agent.reasoning.TIBModel;

public class DummyDecisionComponent extends TIBModel implements DecisionComponent {

	private static Logger LOGGER = Logger.getLogger(DummyDecisionComponent.class.getName());

	public DummyDecisionComponent(Determinant belief, Determinant evaluation, Determinant norm, Determinant role,
			Determinant self_concept, Determinant emotion, Determinant facilitatingCond, Determinant freq,
			double attitudeWeight, double socialWeight, double affectWeight, double intentionWeight,
			double habitWeight) {
		super(belief, evaluation, norm, role, self_concept, emotion, facilitatingCond, freq, attitudeWeight,
				socialWeight, affectWeight, intentionWeight, habitWeight);

	}
}
