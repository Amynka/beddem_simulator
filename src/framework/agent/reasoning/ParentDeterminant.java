package framework.agent.reasoning;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import framework.agent.core.IAgent;
import framework.concept.Option;
import framework.concept.OutcomeProps;
import framework.concept.Task;

/**
 * A class represent a parent determinant in decision making model. Its ranking
 * function depends on
 * 
 * @author khoa_nguyen
 * @see IAgent
 */
public class ParentDeterminant extends Determinant {

	private List<Determinant> children;

	public ParentDeterminant(String id, double weight) {
		super(id, weight);
		this.children = new LinkedList<Determinant>();
	}

	/**
	 * Add a child determinant to the children list.
	 * 
	 * @param child The child determinant that needs to be added.
	 */
	public void addDeterminantChild(Determinant child) {
		if (child != null) {
			this.children.add(child);
		}
	}

	@Override
	public Map<Option, Double> evalOptions(List<Option> inputOptions, List<OutcomeProps> outcomePropsList, Task task) {
		// Initialize the result map to store scores for each option
		Map<Option, Double> results = new HashMap<Option, Double>();
		// Iterate over all child determinants of the current node
		for (Determinant child : this.children) {
			// If the child is null, initialize all options with a score of 0.0
			if (child == null) {
				for (Option opt : inputOptions) {
					if (!results.containsKey(opt)) {
						results.put(opt, 0.0);
					}
				}
			} else {
				double sumValue = 0;
				// Recursively evaluate the child determinant
				Map<Option, Double> childEvaluation = child.evalOptions(inputOptions, outcomePropsList, task);
				// Compute the sum of all scores from the child
				for (Option opt : childEvaluation.keySet()) {
					sumValue += childEvaluation.get(opt);
				}
				// Avoid zero division
				if (sumValue == 0) {
					sumValue = 1;
				}
				// Normalize and weight each option score from child
				for (Option opt : inputOptions) {
					double childValue = childEvaluation.get(opt) * child.getWeight() / sumValue;
					// Add scores to the result
					if (results.containsKey(opt)) {
						results.put(opt, results.get(opt) + childValue);
					} else {
						results.put(opt, childValue);
					}
				}
			}
		}
		return results;
	}

//	@Override
//	protected Map<Double, Set<Option>> evalOpts(Set<Option> inputOpts, Task task) {
//		Map<Double, Set<Option>> result = new HashMap<Double, Set<Option>>();
//		if (this.children.size() == 0) {
//			// If the node has no children, return the original set as the first element in
//			// the result list. This means that all options are considered the same in this
//			// determinant.
//			result.put(1.0, inputOpts);
//		} else {
//			// Otherwise, ask child node to rank the inputs list and calculate new value
//			// based on its rank.
//			Map<Option, Double> optionToValueMap = new HashMap<Option, Double>();
//			for (Determinant child : this.children) {
//				Map<Double, Set<Option>> childOptList = child.rankOptions(inputOpts, task);
//				for (Map.Entry<Double, Set<Option>> mapEntry : childOptList.entrySet()) {
//					Set<Option> sameRankOpts = mapEntry.getValue();
//					// The new value of the option equals to the sum of all its normalised positions
//					// in the
//					// children lists multiplied by their weights.
//					for (Option opt : sameRankOpts) {
//						// Used for cut offs a number of option.
//						if (inputOpts.contains(opt)) {
//							double newValue = mapEntry.getKey() * child.getWeight();
//							if (optionToValueMap.containsKey(opt)) {
//								optionToValueMap.put(opt, optionToValueMap.get(opt) + newValue);
//							} else {
//								optionToValueMap.put(opt, newValue);
//							}
//						}
//					}
//				}
//			}
//			// Picked the final value and the option and put them in the result map. This is
//			// because the result map is value to option map.
//			for (Map.Entry<Option, Double> entry : optionToValueMap.entrySet()) {
//				if (result.containsKey(entry.getValue())) {
//					result.get(entry.getValue()).add(entry.getKey());
//				} else {
//					HashSet<Option> opts = new HashSet<Option>();
//					opts.add(entry.getKey());
//					result.put(entry.getValue(), opts);
//				}
//			}
//		}
//		return result;
//	}
}
