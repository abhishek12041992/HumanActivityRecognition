
public class Dpa {

	
	
	static double[][] tempelate_time_series;
	static double[] point;
	static double[] point2;
	DTW dtw =new DTW();
	Allign allign=new Allign();
	Main_class m=new Main_class();
	public void claculate_point_average(int nClusters,int[] card_cluster,int[][] clustering_index  )
	{
		int d=128*6;
		int minIndex;
		tempelate_time_series=new double [nClusters][128*6];
		point =new double[d];
		point2 =new double[d];
		for (int i=0;i<nClusters;i++)
		{ 
			double dis1[]=new double[card_cluster[i]];
			for (int j1=0;j1<card_cluster[i];j1++)
			{double sum=0;
				for (int k1=0;k1<card_cluster[i];k1++)
				{
					sum+=dtw.start(m.final_data_matrix[clustering_index[i][j1]],m.final_data_matrix[clustering_index[i][k1]]);
					
				}
				dis1[j1]=sum;
				minIndex = this.getIndexOfMinimum(dis1);
				point=m.final_data_matrix[clustering_index[i][minIndex]];
			}
			
			for (int j=0;j<card_cluster[i];j++)
			{
				point2=allign.start(point, m.final_data_matrix[clustering_index[i][j]]);
				for (int k=0;k<d;k++)
				{
					tempelate_time_series[i][k]=(tempelate_time_series[i][k]+point2[k]);
					
				}
				
				
			}
			
			
			
			
		}
		double card;
		for (int i=0;i<nClusters;i++)
		{
			
			card=card_cluster[i];
				for (int k=0;k<d;k++)
				{
					tempelate_time_series[i][k]=tempelate_time_series[i][k]/card;
				}
		}
	/*	for(int i=0;i<nClusters;i++)
		{
			for (int k=0;k<d;k++)
			{
				System.out.print(tempelate_time_series[i][k]);
				
			}
			System.out.print('\n');
		}
		*/
		
		
	}
	
	
	
	
	
	
	protected int getIndexOfMinimum(double[] array) {
		int index = 0;
		double val = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] < val) {
				val = array[i];
				index = i;
			}
		}
		return index;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
