package clinicalDecisionSupportSystem;

public final class CDSSConstants {
	
	private CDSSConstants() {
        // instantiation restricted
	}

	public static final String ML_ALGORITHM_K_NEAREST_NEIGHBOUR = "k-Nearest Neighbors";
	public static final String ML_ALGORITHM_SVM = "SVM";
	public static final String ML_ALGORITHM_NAIVE_BAYES = "Naive Bayes";
	public static final String ML_ALGORITHM_RULE_BASED  = "Rule based ";
	public static final String ML_ALGORITHM_TREE_BASED = "Tree based";
	public static final String ML_ALGORITHM_MULTI_LAYER_PERCEPTRON = "Multi layer perceptron";
	
	public static final int METHOD_REPLACE = 1;
	public static final int METHOD_ENSEMBLE = 2;
	public static final int METHOD_INCREMENTAL = 3;
}
