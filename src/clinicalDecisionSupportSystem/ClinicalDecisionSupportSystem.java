package clinicalDecisionSupportSystem;

import ml.approach.ClinicalDecisionObject;
import ml.approach.MethodFactory;
import ml.approach.Replace;

public class ClinicalDecisionSupportSystem {

	static final String TRAIN1_FILE_PATH = "E:\\dataset\\Allergy_Data1_ubtrain.csv";
	static final String TRAIN2_FILE_PATH = "E:\\dataset\\Allergy_Data2_ubtrain.csv";

	public static void main(String args[]) {

		ClinicalDecisionObject cdss = MethodFactory.getMethod(CDSSConstants.METHOD_REPLACE);

		cdss.initialize(TRAIN1_FILE_PATH, TRAIN2_FILE_PATH, null, null);
		try {
			cdss.performTest(CDSSConstants.ML_ALGORITHM_K_NEAREST_NEIGHBOUR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
