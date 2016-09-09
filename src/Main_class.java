import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import mpi.MPI;

public class Main_class {

	static  DTW test2=new DTW();
	static Dpa d=new Dpa();
	static int nInstances=100;
	static int nInstances_test=5;
	static int nIncremental=10;
	static double body_acc_x_train[][]=new double[nInstances][128];
	static double body_acc_y_train[][]=new double[nInstances][128];
	static double body_acc_z_train[][]=new double[nInstances][128];
	static double body_gyro_x_train[][]=new double[nInstances][128];
	static double body_gyro_y_train[][]=new double[nInstances][128];
	static double body_gyro_z_train[][]=new double[nInstances][128];
	static double body_acc_x_train_in[][]=new double[nIncremental][128];
	static double body_acc_y_train_in[][]=new double[nIncremental][128];
	static double body_acc_z_train_in[][]=new double[nIncremental][128];
	static double body_gyro_x_train_in[][]=new double[nIncremental][128];
	static double body_gyro_y_train_in[][]=new double[nIncremental][128];
	static double body_gyro_z_train_in[][]=new double[nIncremental][128];
	static double body_acc_x_test[][]=new double[nInstances_test][128];
	static double body_acc_y_test[][]=new double[nInstances_test][128];
	static double body_acc_z_test[][]=new double[nInstances_test][128];
	static double body_gyro_x_test[][]=new double[nInstances_test][128];
	static double body_gyro_y_test[][]=new double[nInstances_test][128];
	static double body_gyro_z_test[][]=new double[nInstances_test][128];
	static double final_body_acc[][]=new double[nInstances][128*3];
	static double final_body_gyro[][]=new double[nInstances][128*3];
	static double final_body_acc_in[][]=new double[nIncremental][128*3];
	static double final_body_gyro_in[][]=new double[nIncremental][128*3];
	static double final_data_matrix_in[][]=new double[nIncremental][128*6];
	static double final_data_matrix[][]=new double[nInstances][128*6];
	static double final_body_acc_test[][]=new double[nInstances_test][128*3];
	static double final_body_gyro_test[][]=new double[nInstances_test][128*3];
	static double final_data_matrix_test[][]=new double[nInstances_test][128*6];
	static int activity[][]=new int [nInstances][1];
	static int activity_in[][]=new int [nIncremental][1];
	static int activity1[][]=new int [nInstances_test][1];
	static int [][] clustering=new int[nInstances][nInstances];
	static long startTime;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		double dis;
		String path1="src/body_acc_x_train.txt";
		String path2="src/body_acc_y_train.txt";
		String path3="src/body_acc_z_train.txt";
		String path4="src/body_gyro_x_train.txt";
		String path5="src/body_gyro_y_train.txt";
		String path6="src/body_gyro_z_train.txt";
		String path7="src/y_train.txt";
		String path8="src/y_test.txt";
		String path9="src/body_acc_x_test.txt";
		String path10="src/body_acc_y_test.txt";
		String path11="src/body_acc_z_test.txt";
		String path12="src/body_gyro_x_test.txt";
		String path13="src/body_gyro_y_test.txt";
		String path14="src/body_gyro_z_test.txt";
		/*String path1="src/body_acc_x_train1.txt";
		String path2="src/body_acc_y_train1.txt";
		String path3="src/body_acc_z_train1.txt";
		String path4="src/body_gyro_x_train1.txt";
		String path5="src/body_gyro_y_train1.txt";
		String path6="src/body_gyro_z_train1.txt";*/
		ReadingMatrix rm=new ReadingMatrix();
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank(),
	             size = MPI.COMM_WORLD.Size();
	if(rank==0)
	{ 
		startTime = System.currentTimeMillis();
		body_acc_x_train=rm.twoDArrayofTimeSeries(path1,1);
		body_acc_y_train=rm.twoDArrayofTimeSeries(path2,1);
		body_acc_z_train=rm.twoDArrayofTimeSeries(path3,1);
		body_acc_x_test=rm.twoDArrayofTimeSeries(path9,2);
		body_acc_y_test=rm.twoDArrayofTimeSeries(path10,2);
		body_acc_z_test=rm.twoDArrayofTimeSeries(path11,2);
		for(int i=0;i<nInstances;i++)
		{
			for(int j=0;j<128*3;j++)
			{
				if(j<128)
				{
					final_body_acc[i][j]=body_acc_x_train[i][j];
				}
				if(j>=128&&j<256)
				{
					final_body_acc[i][j]=body_acc_y_train[i][j%128];
				}
				if(j>=256&&j<384)
				{
					final_body_acc[i][j]=body_acc_z_train[i][j%128];
				}
			}
		}
		for(int i=0;i<nInstances_test;i++)
		{
			for(int j=0;j<128*3;j++)
			{
				if(j<128)
				{
					final_body_acc_test[i][j]=body_acc_x_test[i][j];
				}
				if(j>=128&&j<256)
				{
					final_body_acc_test[i][j]=body_acc_y_test[i][j%128];
				}
				if(j>=256&&j<384)
				{
					final_body_acc_test[i][j]=body_acc_z_test[i][j%128];
				}
			}
		}
		body_gyro_x_train=rm.twoDArrayofTimeSeries(path4,1);
		body_gyro_y_train=rm.twoDArrayofTimeSeries(path5,1);
		body_gyro_z_train=rm.twoDArrayofTimeSeries(path6,1);
		body_gyro_x_test=rm.twoDArrayofTimeSeries(path12,2);
		body_gyro_y_test=rm.twoDArrayofTimeSeries(path13,2);
		body_gyro_z_test=rm.twoDArrayofTimeSeries(path14,2);
		activity=rm.class_label(path7,1);
		activity1=rm.class_label(path8,2);
		for(int i=0;i<nInstances;i++)
		{
			for(int j=0;j<128*3;j++)
			{
				if(j<128)
				{
					final_body_gyro[i][j]=body_gyro_x_train[i][j];
				}
				if(j>=128&&j<256)
				{
					final_body_gyro[i][j]=body_gyro_y_train[i][j%128];
				}
				if(j>=256&&j<384)
				{
					final_body_gyro[i][j]=body_gyro_z_train[i][j%128];
				}
			}
		}
		for(int i=0;i<nInstances_test;i++)
		{
			for(int j=0;j<128*3;j++)
			{
				if(j<128)
				{
					final_body_gyro_test[i][j]=body_gyro_x_test[i][j];
				}
				if(j>=128&&j<256)
				{
					final_body_gyro_test[i][j]=body_gyro_y_test[i][j%128];
				}
				if(j>=256&&j<384)
				{
					final_body_gyro_test[i][j]=body_gyro_z_test[i][j%128];
				}
			}
		}
		for(int i=0;i<nInstances;i++)
		{
			for(int j=0;j<128*6;j++)
			{
				if(j<128*3)
				{
					final_data_matrix[i][j]=final_body_acc[i][j];
				}
				if(j>=128*3&&j<128*6)
				{
					final_data_matrix[i][j]=final_body_gyro[i][j%(128*3)];
				}
				
			}
		}
		for(int i=0;i<nInstances_test;i++)
		{
			for(int j=0;j<128*6;j++)
			{
				if(j<128*3)
				{
					final_data_matrix_test[i][j]=final_body_acc_test[i][j];
				}
				if(j>=128*3&&j<128*6)
				{
					final_data_matrix_test[i][j]=final_body_gyro_test[i][j%(128*3)];
				}
				
			}
		}
		
		System.out.println(body_acc_x_train[0][0]);
	     dis=test2.start(body_acc_x_train[0],body_acc_x_train[9]);
	     System.out.println(dis);
		 dis=test2.start(body_gyro_x_train[0],body_gyro_x_train[9]);
		 System.out.println(dis);
		 dis=test2.start(final_data_matrix[0],final_data_matrix[9]);
		 System.out.println(dis);
		
		 double temp2[]=new double[128*6];
			temp2=new Allign().start(final_data_matrix[0],final_data_matrix[7]);
		
			for(int i=0;i<128*6;i++)
		{
			System.out.print(temp2[i]);
		}
			
			
	}
		//System.out.println("the difference is"+t );
		 Experiment experiment = new clustering_start();
		 DissimilarityMeasure dissimilarityMeasure = new Calculate_sim();
		 
		 AgglomerationMethod agglomerationMethod =new SingleLinkage();
		// DendrogramBuilder dendrogramBuilder = new DendrogramBuilder(experiment.getNumberOfObservations());
		  ClusteringMatrixBuilder step_recorder=new ClusteringMatrixBuilder(nInstances);
		 HierarchicalAgglomerativeClusterer clusterer = new HierarchicalAgglomerativeClusterer(experiment, dissimilarityMeasure, agglomerationMethod,rank,size);
		 clusterer.cluster(step_recorder);
		clustering= step_recorder.getClustering();
		System.out.println("out");
		
		if(rank==0){
			File file2 = new File("c:/result/clustering_process.txt");
			String content2 = "This is the text content";
			byte[] contentInBytes2;
			try (FileOutputStream fop = new FileOutputStream(file2)) {

				// if file doesn't exists, then create it
				if (!file2.exists()) {
					file2.createNewFile();
				}
				for(int i=0;i<nInstances;i++)
		        {
					content2="\n\n Step "+i+" : ";
					contentInBytes2 = content2.getBytes();
					fop.write(contentInBytes2);
					fop.flush();
		     	   for(int j=0;j<nInstances;j++)
		     	   {
		     		  
		     		   
		     		   
		     		   content2=clustering[i][j]+"  ";
		     		  contentInBytes2 = content2.getBytes();
		     		 fop.write(contentInBytes2);
						
		     	   
		     	   }
		        }
				
				
				fop.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			long stopTime = System.currentTimeMillis();
			long elapsed=(stopTime-startTime)/1000;
			System.out.println(elapsed+"  seconds");
		/*	double [][] dissimilarityMatrix1=new double[nInstances][128*6];
			double [][] dissimilarityMatrix=new double[nInstances][128*6];
			for (int i=1;i<size;i++)
			{
				MPI.COMM_WORLD.Recv(dissimilarityMatrix1, 0, nInstances, MPI.OBJECT, i, 0);
				for (int j=0;j<nInstances;j++)
				{
					for (int k=0;k<nInstances;k++)
					{
					       clustering[][]
					}
				}
			}
*/			
		int count=0;
		int unique=0;
		
		int cut=95;
	
		for(int i=0;i<nInstances;i++)//finding no of unique clusters
		{  unique=0;
			for(int j=0;j<i;j++)
			{
				
				if(clustering[cut][i]==clustering[cut][j])
				{
					unique=1;
				}
			}
			if(unique==0)
			{
			
				count++;
			}
			
		}
		//System.out.println(count);
		
		int[] cluster=new int[count];
		int index=0;
		for(int i=0;i<nInstances;i++)//finding the name of the cluster
		{ 
			unique=0;
			for(int j=0;j<i;j++)
			{
				 
				if(clustering[cut][i]==clustering[cut][j])
				{
					unique=1;
				}
			}
			if(unique==0)
			{
				//System.out.println(index);
			   cluster[index++]=clustering[cut][i];
				
			}
			
		}
		
		 int [] cardinality=new int[count];
		 int [][] clustering_index=new int[count][nInstances];
		 int [][] clustering_class=new int [count][nInstances];
		 int [][] clustering_label=new int [count][1];
		//System.out.println(count);
		int p=0;
		for(int i=0;i<count;i++)//finding the cardinality and the elements of the cluster
		{ 
			p=0;
			for(int j=0;j<nInstances;j++)
			{
				if(cluster[i]==clustering[cut][j])
				{
					cardinality[i]++;
					clustering_index[i][p++]=j;
					
				}
			}
		}
		p=0;
		for (int i=0;i<count;i++)
		{
			p=0;
			for (int j=0;j<cardinality[i];j++)
			{
				
				clustering_class[i][p++]=activity[clustering_index[i][j]][0];
			//	System.out.println(clustering_class[i][j]);
				//System.out.println(activity[clustering_index[i][j]][0]);
		
			}
			
			
			
			
		}
		for(int k=0;k<count;k++)
		{
			//System.out.println("hi");
			 int count1 = 1, tempCount;
			  int popular = clustering_class[k][0];
			  int temp = 0;
		     for(int i=0;i<(cardinality[k]-1);i++)
		       {
		    	 	//System.out.println("hi");
		    	 	temp = clustering_class[k][i];
		    	 	tempCount = 0;

			
		    	 			for(int j=1;j<cardinality[k];j++)
		    	 				{
		    	 					//System.out.println(clustering_class[k][j]);
		    	 						if (temp == clustering_class[k][j])
		    	 							{ 
		    	 								tempCount++;
		    	 							}
		    	 				}
		    	 if (tempCount > count1)
		    	 	{
		    		 	popular = temp;
		    		 	count1 = tempCount;
		    	 	}
		       }
		//System.out.println(popular);
		clustering_label[k][0]=popular;
		}
	/*	for(int i=0;i<nInstances;i++)
		{
			System.out.println(activity[i][0]);
		}*/
			for(int i=0;i<count;i++)
			{
				//System.out.println( cluster[i]);
				//System.out.println( cardinality[i]);
				//System.out.println(clustering_label[i][0] );
			}
			new Dpa().claculate_point_average(count, cardinality, clustering_index);//calculate the template of each cluster
			File file = new File("c:/result/first_run_tempelate.txt");
			String content = "This is the text content";
			byte[] contentInBytes;
			try (FileOutputStream fop = new FileOutputStream(file)) {

				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				for(int i=0;i<count;i++)
		        {
					content="\n\n\n\nCluster "+i+" : ";
					contentInBytes = content.getBytes();
					fop.write(contentInBytes);
					fop.flush();
		     	   for(int j=0;j<128*6;j++)
		     	   {
		     		  
		     		   
		     		   
		     		   content=d.tempelate_time_series[i][j]+" ;";
		     		  contentInBytes = content.getBytes();
		     		 fop.write(contentInBytes);
						
		     	   
		     	   }
		        }
				
				
				fop.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			double classify_temp[][]=new double [nInstances_test][count];
			int classify[][]=new int [nInstances_test][1];
			for (int i=0;i<nInstances_test;i++)
			{
				for(int j=0;j<count;j++)
				{
					classify_temp[i][j]=test2.start(final_data_matrix[i], d.tempelate_time_series[j]);
				}
			}
			int accuracy=0;
			for (int i=0;i<nInstances_test;i++)
			{
				 int index2 = 0;
			       double min = classify_temp[i][index2];
			       for (int j=1; j<count; j++){

			           if (classify_temp[i][j] < min ){
			               min = classify_temp[i][j];
			               index2 = j;
			           }
                          
                     
			       }
			       if(clustering_label[index2][0]==4||clustering_label[index2][0]==5||clustering_label[index2][0]==6)
			       {
			       classify[i][0]=4;
			       }
			       else
			       {
			    	   classify[i][0]=clustering_label[index2][0];
			       }
			       if(classify[i][0]==4)
			       {
			    	   if(activity[i][0]==5||activity[i][0]==4||activity[i][0]==6)
			    	   {
			    		   accuracy++;
			    	   }
			       }
			       else{
			    	   if(activity[i][0]==classify[i][0])
			    	   {
			    		   accuracy++;
			    	   }
			       }
			       int lab=0;
			       if(activity[i][0]==5||activity[i][0]==6||activity[i][0]==4)
			       {
			    	   lab=4;
			       }
			       else{
			    	   lab=activity[i][0];
			       }
			       System.out.println("Test instance with label "+lab+" classified as: "+classify[i][0]);
			}
			double acc;
			acc=accuracy/(double)nInstances_test;
			System.out.println("Accuracy:"+accuracy);
			System.out.println("Accuracy:"+acc*100);
		System.out.println("Done");
		
		//BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    //String s = bufferRead.readLine();
		
		
		
			System.out.println("hello");
			while (true)
			{
				System.out.println("hello");
				body_acc_x_train_in=rm.twoDArrayofTimeSeries(path1,3);
				body_acc_y_train_in=rm.twoDArrayofTimeSeries(path2,3);
				body_acc_z_train_in=rm.twoDArrayofTimeSeries(path3,3);
				body_gyro_x_train_in=rm.twoDArrayofTimeSeries(path4,3);
				body_gyro_y_train_in=rm.twoDArrayofTimeSeries(path5,3);
				body_gyro_z_train_in=rm.twoDArrayofTimeSeries(path6,3);
				activity_in=rm.class_label(path7,3);
				for(int i=0;i<nIncremental;i++)
				{
					for(int j=0;j<128*3;j++)
					{
						if(j<128)
						{
							final_body_acc_in[i][j]=body_acc_x_train_in[i][j];
						}
						if(j>=128&&j<256)
						{
							final_body_acc_in[i][j]=body_acc_y_train_in[i][j%128];
						}
						if(j>=256&&j<384)
						{
							final_body_acc_in[i][j]=body_acc_z_train_in[i][j%128];
						}
					}
				}
				for(int i=0;i<nIncremental;i++)
				{
					for(int j=0;j<128*3;j++)
					{
						if(j<128)
						{
							final_body_gyro_in[i][j]=body_gyro_x_train_in[i][j];
						}
						if(j>=128&&j<256)
						{
							final_body_gyro_in[i][j]=body_gyro_y_train_in[i][j%128];
						}
						if(j>=256&&j<384)
						{
							final_body_gyro_in[i][j]=body_gyro_z_train_in[i][j%128];
						}
					}
				}
				for(int i=0;i<nIncremental;i++)
				{
					for(int j=0;j<128*6;j++)
					{
						if(j<128*3)
						{
							final_data_matrix_in[i][j]=final_body_acc_in[i][j];
						}
						if(j>=128*3&&j<128*6)
						{
							final_data_matrix_in[i][j]=final_body_gyro_in[i][j%(128*3)];
						}
						
					}
				}
				double classify_temp_in[][]=new double [nIncremental][count];
				for (int i=0;i<nIncremental;i++)
				{
					System.out.println(i);
					for(int j=0;j<count;j++)
					{
						classify_temp_in[i][j]=test2.start(final_data_matrix_in[i], d.tempelate_time_series[j]);
					}
				}
				//double[] point =new double[128*6];
				for (int i=0;i<nIncremental;i++)
				{
					System.out.println(i);
					 int index2 = 0;
				       double min = classify_temp_in[i][index2];
				       for (int j=1; j<count; j++){

				           if (classify_temp_in[i][j] < min ){
				               min = classify_temp_in[i][j];
				               index2 = j;
				           }
				          
				         //point=  new Incremental_updation().do_inc(final_data_matrix_in[i], d.tempelate_time_series[j]);
				         
				         
				         
				         for (int k=0;k<128*6;k++)
							{   
				        	    
								d.tempelate_time_series[j][k]=d.tempelate_time_series[j][k]*cardinality[j];
							
							}
				         for (int k=0;k<128*6;k++)
							{
								d.tempelate_time_series[j][k]=d.tempelate_time_series[j][k]+final_data_matrix_in[j][k];
							}
				         cardinality[j]++;
				         for (int k=0;k<128*6;k++)
							{
								d.tempelate_time_series[j][k]=(d.tempelate_time_series[j][k]/cardinality[j]);
							}
				       }
				      for(int q=0;q<count;q++)
				      {
				    	  for(int r=0;r<128*6;r++)
				    	  {
				    		  d.tempelate_time_series[q][r]=  d.tempelate_time_series[q][r];
				    	  }
				    	  
				      }
				       
				}
				File file1 = new File("c:/result/after_batch_updation_tempelate.txt");
				String content1 = "This is the text content";
				byte[] contentInBytes1;
				try (FileOutputStream fop = new FileOutputStream(file1)) {

					// if file doesn't exists, then create it
					if (!file1.exists()) {
						file1.createNewFile();
					}
					for(int i=0;i<count;i++)
			        {
						content1="\n\n\n\nCluster "+i+" : ";
						contentInBytes1 = content1.getBytes();
						fop.write(contentInBytes1);
						fop.flush();
			     	   for(int j=0;j<128*6;j++)
			     	   {
			     		  
			     		   
			     		   
			     		   content1=d.tempelate_time_series[i][j]*10.0+" ;";
			     		  contentInBytes1 = content1.getBytes();
			     		 fop.write(contentInBytes1);
							
			     	   
			     	   }
			        }
					
					
					fop.close();

					System.out.println("Done");

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
					
		}	
	}
	

}
