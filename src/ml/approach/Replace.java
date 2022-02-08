package ml.approach;

import java.util.Random;

import ml.algorithm.ClassifierFactory;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;

public class Replace extends ClinicalDecisionObject {

	int count = 1;

	public void performTest() throws Exception {

		// Train dataset 1 using cross validation
		trainAndTest(train1);

		// Train dataset 2 using cross validation
		trainAndTest(train2);

	}

	private void trainAndTest(Instances data) throws Exception {

		Evaluation eval1 = new Evaluation(data);
		// The crossValidateModel takes care of training and evaluating the classifier.
		eval1.crossValidateModel(this.classifier, data, 10, new Random(10));

		System.out.println(eval1.toSummaryString("============ Scenario #" + count++ + "============\n", false));
		System.gc();
	}
}