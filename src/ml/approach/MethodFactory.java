package ml.approach;

import clinicalDecisionSupportSystem.CDSSConstants;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.trees.J48;

public class MethodFactory {
	public static ClinicalDecisionObject getMethod(String method) {
		switch (method) {
		case CDSSConstants.METHOD_REPLACE:
			return new Replace();
		case CDSSConstants.METHOD_ENSEMBLE:
			return new Ensemble();
		case CDSSConstants.METHOD_INCREMENTAL:
			return new Incremental();
		default:
			return null;
		}
	}
}
