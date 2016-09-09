import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadingMatrix {
    
     double[][] twoDArrayofTimeSeries(String path,int test) throws FileNotFoundException
    {
    	 int rows=0;
    	 int is=0;
    	 if(test==2)
    	 {
    	  rows=new Main_class().nInstances_test;
    	 }
    	 if(test==1){
    		 rows=new Main_class().nInstances;
    	 }
    	 if(test==3)
    	 {
    		 rows=new Main_class().nIncremental+new Main_class().nInstances_test;;
    		 is=new Main_class().nInstances_test;;
    	 }
        int count=0;
        double perc;
        Scanner in = new Scanner(new File(path));
        List<String[]> lines = new ArrayList<>();
        int c=0;
        while(in.hasNextLine()&& count<rows ) {
            String line = in.nextLine().trim();

           //perc= (count++ /7352.0)*100;

        //  System.out.println("here");
            String[] splitted = line.split("  | -");
            for(int i = 0; i<splitted.length; i++) {
                splitted[i] = splitted[i].substring(0, splitted[i].length());
            }
            lines.add(splitted);
        }
        in.close();

        //pretty much done, now convert List<String[]> to String[][]
        String[][] result = new String[lines.size()][];
        for(int i = 0; i<result.length; i++) {
            result[i] = lines.get(i);
        }
        int r=result.length;
        int dimensions=result[0].length;
        //System.out.println(result.length);
        System.out.println("---------------------------------------------");
        System.out.println("No of dimensions are  = "+ dimensions);
        double[][] matrix=new double[rows-is][dimensions];
        System.out.println("----------------------------------------------");
        System.out.println("Parsing to double");
        for(int i=0;i<rows-is;i++)
        {
        	
            for(int j=0;j<dimensions;j++)
            {
            	
                matrix[i][j]=Double.parseDouble(result[i+is][j]);
            }
        }
      

    return matrix;
    }
     int[][] class_label(String path,int test) throws FileNotFoundException
     {
         int count=0;
         int instances=0;
         double perc;
         int is=0;
         if(test==2)
    	 {
    	  instances=new Main_class().nInstances_test;
    	 }
    	 if(test==1){
    		 instances=new Main_class().nInstances;
    	 }
    	 if(test==3)
    	 {
    		 instances=new Main_class().nIncremental+new Main_class().nInstances_test;
    		 is=new Main_class().nInstances_test;
    	 }
         Scanner in = new Scanner(new File(path));
         List<String[]> lines = new ArrayList<>();
         int c=0;
         while(in.hasNextLine()&& count<instances) {
             String line = in.nextLine().trim();

            perc= (count++ /7352.0)*100;

           // System.out.println(perc);
             String[] splitted = line.split("  | -");
             for(int i = is; i<splitted.length; i++) {
                 splitted[i] = splitted[i].substring(0, splitted[i].length());
             }
             lines.add(splitted);
         }
         in.close();

         //pretty much done, now convert List<String[]> to String[][]
         String[][] result = new String[lines.size()][];
         for(int i = 0; i<result.length; i++) {
             result[i] = lines.get(i);
         }
         int dimensions=result[0].length;
         System.out.println(result.length);
         System.out.println("No of dimensions are  = "+ dimensions);
         int[][] matrix=new int[instances-is][dimensions];
         System.out.println("Parsing to double");
         for(int i=0;i<instances-is;i++)
         {
         	
             for(int j=0;j<dimensions;j++)
             {
             	
                 matrix[i][j]=Integer.parseInt(result[i+is][j]);
             }
         }

     return matrix;
     }
}
