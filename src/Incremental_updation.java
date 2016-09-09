
public class Incremental_updation {
   static double []point =new double[128*6];
   static Allign allign= new Allign();
	public static double[]  do_inc( double[]t1,double []t2   )
	{
		point=allign.start(t1, t2);
		return point;
		
		
	}
	
	
	
	
	
	
	
	
	
}
