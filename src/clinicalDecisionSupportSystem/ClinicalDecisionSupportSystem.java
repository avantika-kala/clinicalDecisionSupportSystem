package clinicalDecisionSupportSystem;

import ml.approach.ClinicalDecisionObject;
import ml.approach.MethodFactory;
import ml.approach.Replace;

public class ClinicalDecisionSupportSystem {

	public static void main(String args[]) {

		ClinicalDecisionObject cdss = MethodFactory.getMethod(CDSSConstants.METHOD_REPLACE);

		cdss.initialize("E:\\dataset\\Allergy_Data1_ubtrain.csv", "E:\\dataset\\Allergy_Data2_ubtrain.csv", null, null);
		try {
			cdss.performTest(CDSSConstants.ML_ALGORITHM_K_NEAREST_NEIGHBOUR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
