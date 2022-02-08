package ml.approach;

import java.io.File;
import java.io.IOException;

import ml.algorithm.ClassifierFactory;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class ClinicalDecisionObject {

	Instances train1, train2, test1, test2;
	ClinicalDecisionObject testingMethod;
	Classifier classifier;

	public int initialize(String fileSourceTrain1, String fileSourceTrain2, String fileSourceTest1,
			String fileSourceTest2, String algorithm) {
		
		classifier = ClassifierFactory.getClassifier(algorithm);
		if (classifier == null) {
			System.out.println("No Classifier found! returning...");
			return -1;
		}
		try {
			// initialize training data 1
			CSVLoader train1CSV = new CSVLoader();
			train1CSV.setSource(new File(fileSourceTrain1));
			train1 = train1CSV.getDataSet();
			train1.setClassIndex(train1.numAttributes() - 1);

			// Get training data 2
			CSVLoader train2CSV = new CSVLoader();
			train2CSV.setSource(new File(fileSourceTrain2));
			train2 = train2CSV.getDataSet();
			train2.setClassIndex(train2.numAttributes() - 1);
			
			return 1;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public void performTest()  throws Exception {
	}

}
