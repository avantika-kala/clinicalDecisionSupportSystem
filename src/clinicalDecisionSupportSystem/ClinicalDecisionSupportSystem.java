package clinicalDecisionSupportSystem;

import ml.approach.ClinicalDecisionObject;
import ml.approach.MethodFactory;
import ml.approach.Replace;

public class ClinicalDecisionSupportSystem {

	static final String TRAIN1_FILE_PATH = "E:\\dataset\\Allergy_Data1_ubtrain.csv";
	static final String TRAIN2_FILE_PATH = "E:\\dataset\\Allergy_Data2_ubtrain.csv";
	
	static final String METHOD = CDSSConstants.METHOD_REPLACE;
	static final String ML_ALGORITHM = CDSSConstants.ML_ALGORITHM_K_NEAREST_NEIGHBOUR;

	public static void main(String args[]) {
		
		System.out.println("========== Setup details ==========");
		System.out.println("Method: " + METHOD);
		System.out.println("Algorithm: " + ML_ALGORITHM +"\n");

		ClinicalDecisionObject cdss = MethodFactory.getMethod(METHOD);
		cdss.initialize(TRAIN1_FILE_PATH, TRAIN2_FILE_PATH, null, null);
		try {
			cdss.performTest(ML_ALGORITHM);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
