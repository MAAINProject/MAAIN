package models;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static float mat[][];
	private static Map<Integer,List<Integer>> myMap=new HashMap<>();
	private static int x[]=new int[2];
	private static int y=x[0];
	private static MCI compresse= new MCI();

	 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	mat = new float[][] {{0,3,5,8},{1,0,2,0},{0,0,0,0},{0,3,0,0}};
		
		
		
		
		List<Integer> liste=new ArrayList<Integer>();
		myMap.put(y,liste);
	/*	for(int i=0;i<compresse.getC().size();i++)
		{
			
			System.out.print(compresse.getC().get(i)+"\t");
		}
        System.out.println();
		for(int j=0;j<compresse.getL().size();j++)
		{
			
			System.out.print(compresse.getL().get(j)+"\t");
		}
		 System.out.println();
		for(int i=0;i<compresse.getI().size();i++)
		{
			
			System.out.print(compresse.getI().get(i)+"\t");
		}
		
		int k1=0,k2;
		*/
		 BufferedReader br = new BufferedReader(new FileReader("res/p2p-Gnutella08.txt"));
		  String buf;
		  buf=br.readLine();
		  int j;
		  while(buf!=null)
		  { 
		  if(buf.charAt(0)=='#')
		  {
			  buf=br.readLine();
		  }
		  else
		  {
			  j=0;
		  
			  Pattern pattern = Pattern.compile("[0-9]+");
		        Matcher m =  pattern.matcher(buf);
		       while( m.find())
		       {
		          x[j]=Integer.parseInt(m.group());
		          j++;
		          
		       }
		       
		       if(x[0]==y)
		       {
		       
		    	   myMap.get(y).add(x[1]);
		  
		       }
		       
		       else
		       {  y=x[0];
		       List<Integer> newli=new ArrayList<Integer>();
		       newli.add(x[1]);
		    	  
	    	   myMap.put(y,newli);
		    	  
		       }
		  
		       buf=br.readLine();
		  }
		  }
		  
	  Set<java.util.Map.Entry<Integer, List<Integer>>> setHm = myMap.entrySet();

	      Iterator<java.util.Map.Entry<Integer, List<Integer>>> it = setHm.iterator();

	      while(it.hasNext()){

	         java.util.Map.Entry<Integer, List<Integer>> e = it.next();

	         System.out.print(e.getKey() + " :\t" );
	         
	       for(int z=0;z<e.getValue().size();z++)
	         {
	        	 System.out.print(e.getValue().get(z)+"\t");
	         }
	       System.out.println("\n");

	      }
	      
	      List<Float> lis=new ArrayList<Float>();
	      
	   int max=compresse.maxI();


	   lis.add(0,1.f);
	   
	   for(int i=1;i< max;i++)
	   {
		   lis.add(i,0.f);
	   }
		  
		  remplirCLI();

		   compresse.afficheCLI();
		   
		  List<Float> res= compresse.prodTransCLI(lis);
		  for(int i=0;i<res.size();i++)
		  {
			  System.out.print(res.get(i)+"\t");
		  }
		   
	      
	}
	
	
	
	public static void remplirCLI()
	{
		
		int ligne=0;
		int contenu=0;

		int parcours=0;
		
		
		 
		  Set<java.util.Map.Entry<Integer, List<Integer>>> setHm = myMap.entrySet();

	      Iterator<java.util.Map.Entry<Integer, List<Integer>>> it = setHm.iterator();
	      compresse.getL().add(ligne);
	      while(it.hasNext()){

	         java.util.Map.Entry<Integer, List<Integer>> e = it.next();
                   if(e.getKey()==parcours)
                   {
                	   
                	   for(int f=0;f< e.getValue().size();f++)
                	   {
                		   compresse.getC().add(((float)1/(float)( e.getValue().size())));
                		   contenu++;
                		   compresse.getI().add(e.getValue().get(f)); 
                		   }
                	   
                	   
                	   compresse.getL().add(contenu+1);
                	   parcours++;
                	   
                	   
                   }
                   
                   else if(e.getKey()!=parcours)
                   {
                	   compresse.getL().add(compresse.getL().get(compresse.getL().size()-1));
                	   parcours++;
                   }
                   
                   }
	      
	   

			
	      
		
	}
}



