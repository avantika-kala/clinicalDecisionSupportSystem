package ml.approach;

import java.util.Random;

import ml.algorithm.ClassifierFactory;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;

public class Replace extends ClinicalDecisionObject {

	public void performTest(String algorithm) throws Exception {
		// M1(train1)
		Classifier classifier1 = ClassifierFactory.getClassifier(algorithm);
		if (classifier1 == null) {
			System.out.println("No Classifier found! Exiting...");
		}
		classifier1.buildClassifier(this.train1);
		System.out.println("M1(train1) " + classifier1);

		Evaluation eval1 = new Evaluation(this.train1);
		eval1.crossValidateModel(classifier1, this.train1, 10, new Random(1));
		System.out.println(eval1.toSummaryString("============ Scenario #1 ============\n", false));

		// M1(train2)
		Classifier classifier2 = ClassifierFactory.getClassifier(algorithm);
		if (classifier2 == null) {
			System.out.println("No Classifier found! Exiting...");
		}
		classifier2.buildClassifier(this.train2);
		System.out.println("M1(train2) " + classifier2);

		Evaluation eval2 = new Evaluation(this.train2);
		eval2.crossValidateModel(classifier2, this.train2, 10, new Random(1));
		System.out.println(eval1.toSummaryString("============ Scenario #2 ============\n", false));
		
	}
}
