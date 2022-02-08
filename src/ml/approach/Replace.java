package ml.approach;

import java.util.Random;

import ml.algorithm.ClassifierFactory;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;

public class Replace extends ClinicalDecisionObject {

	int count = 1;

	public void performTest(String algorithm) throws Exception {

		// Train dataset 1 using cross validation
		trainAndTest(algorithm, train1);

		// Train dataset 2 using cross validation
		trainAndTest(algorithm, train2);

	}

	private void trainAndTest(String algorithm, Instances data) throws Exception {
		Classifier classifier = ClassifierFactory.getClassifier(algorithm);
		if (classifier == null) {
			System.out.println("No Classifier found! Exiting...");
		}

		Evaluation eval1 = new Evaluation(data);
		// The crossValidateModel takes care of training and evaluating the classifier.
		eval1.crossValidateModel(classifier, data, 10, new Random(10));

		System.out.println(eval1.toSummaryString("============ Scenario #" + count++ + "============\n", false));
		System.gc();
	}
}