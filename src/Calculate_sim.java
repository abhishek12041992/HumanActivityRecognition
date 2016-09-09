
public class Calculate_sim implements DissimilarityMeasure{

	
	static Main_class f= new Main_class();
	static DTW d=new DTW();
	static double dis;
	@Override
	public double computeDissimilarity(Experiment experiment, int observation1, int observation2) {
	   double[] temp1 =new double[128*6];
	   double[] temp2 =new double[128*6];
	   temp1=f.final_data_matrix[observation1];
	   temp2=f.final_data_matrix[observation2];
	   dis=d.start(temp1,temp2);
		return dis;
	}

}
