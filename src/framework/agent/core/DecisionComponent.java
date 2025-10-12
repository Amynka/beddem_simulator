package framework.agent.core;

import java.util.List;
import java.util.Map;

import framework.concept.Option;
import framework.concept.OutcomeProps;
import framework.concept.Task;

public interface DecisionComponent {

	/**
	 * * Evaluate all options in the provided set.
	 * 
	 * @param options The options provided for evaluation.
	 * @param task    The task to be performed.
	 * @return @return The map for values to all the options scored that value.
	 * @param options
	 */
	// Map<Double, Set<Option>> evaluateOptions(Set<Option> options, Task task);
	Map<Option, Double> evalOptions(List<Option> inputOptions, List<OutcomeProps> outcomePropsList, Task task);

}
