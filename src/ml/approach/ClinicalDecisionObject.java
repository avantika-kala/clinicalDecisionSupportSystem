package ml.approach;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class ClinicalDecisionObject {

	Instances train1, train2, test1, test2;
	ClinicalDecisionObject testingMethod;

	public void initialize(String fileSourceTrain1, String fileSourceTrain2, String fileSourceTest1,
			String fileSourceTest2) {

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

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void performTest(String algorithm)  throws Exception {
	}

}
