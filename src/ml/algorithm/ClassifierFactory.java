package ml.algorithm;

import clinicalDecisionSupportSystem.CDSSConstants;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;

public class ClassifierFactory {

	public static Classifier getClassifier(String classifier) {
		if (classifier == null) {
			return null;
		}
		if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_K_NEAREST_NEIGHBOUR)) {
			
			/* Example - 
			 
				Classifier ibk = new IBk();		
				ibk.buildClassifier(data);
		 
				double class1 = ibk.classifyInstance(first);
				double class2 = ibk.classifyInstance(second);
		 
				System.out.println("first: " + class1 + "\nsecond: " + class2);
				
			 * */
			IBk knn = new IBk();
			knn.setKNN(5);

			return knn;

		} else if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_SVM)) {
			
			return new LibSVM();

		} else if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_NAIVE_BAYES)) {
			
			return new NaiveBayes();
			
		} else if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_RULE_BASED)) {
			
			return new JRip();

		} else if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_TREE_BASED)) {
			
			return new J48();

		} else if (classifier.equalsIgnoreCase(CDSSConstants.ML_ALGORITHM_MULTI_LAYER_PERCEPTRON)) {
			
			return new MultilayerPerceptron();
			
		}

		return null;
	}
}
