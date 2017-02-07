package models;

import java.util.ArrayList;
import java.util.List;

public class MCI {

	private ArrayList<Float> C;
	private ArrayList<Integer> L;
	private ArrayList<Integer> I;
	
	public MCI() {
		super();
		// TODO Auto-generated constructor stub
		C=new ArrayList<Float>();
		L=new ArrayList<Integer>();
		I=new ArrayList<Integer>();
	}
	
	public ArrayList<Float> getC() {
		return C;
	}
	/*public void setC(int n,float Mat[][]) {
		int i,j;
		
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				
			if(Mat[i][j]!=0)
				C.add(Mat[i][j]);
			}
		}
	}*/
	public ArrayList<Integer> getL() {
		return L;
	}
	/*public void setL(int n , float Mat[][]) {
		L.add(0);
		int count;
	          for(int i=0;i<n;i++)
	          { count=0;
	        	  for(int j=0;j<n;j++)
	        	  {
	        		 if(Mat[i][j]!=0) 
	        			 count+=1;
	        	  }
	        	  if(count==0)
	        	  {
	        		  L.add(L.get(L.size()-1));
	        	  }
	        	  else 
	        	  {
	        		  L.add(count+L.get(L.size()-1));
	        	  }
	        		  
	        	  
	        	  
	        	   
	          }
	}*/
	public ArrayList<Integer> getI() {
		return I;
	}
	public void setI(int n, float Mat[][]) {
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(Mat[i][j]!=0)
				{
					I.add(j);
				}
			}
		}
	}
	
 ArrayList<Float> mulyipleMatVect( ArrayList<Float> vect)
	{
		
		ArrayList<Float> res=new ArrayList<Float>(C.size());
		 float local=0;
		for(int i=0;i<vect.size();i++)
		{
			vect.set(i, (float) 0);
			
			for(int j=L.get(i);j< L.get(i+1);j++)
			{
				local=local+C.get(j)* vect.get(I.get(j));
				res.set(i, local);
			}
		}
		return res;
		
		
	}
 ArrayList<Float> multytransvect(ArrayList<Float> vect)
 {
	 ArrayList<Float> res= new ArrayList<Float>(vect.size());
	 
	 return res;
	 
 }
 void afficheCLI()
 {
	 for(int k=0;k< C.size();k++)
	 {
		 System.out.print(C.get(k)+"\t");
	 }
	 
	 System.out.print("\n");
	 for(int l=0;l< L.size();l++)
	 {
		 System.out.print(L.get(l)+"\t");
	 }
	 System.out.print("\n");
	 for(int k=0;k< I.size();k++)
	 {
		 System.out.print(I.get(k)+"\t");
	 }
 }
 
 int maxI()
 {
	 int MAX=0;
	 for(int k=0;k< I.size();k++)
	 {
		 if(I.get(k)> MAX)
			 MAX=I.get(k);
	 }
	 return MAX;
 }
	
 List<Float> prodTransCLI(List<Float> liste)
 {
	 
	 List<Float> res=new ArrayList<Float>();
	 int n=maxI();
	 for(int i=0;i<n;i++)
	 {
		 res.add(i,0.f);
		 
	 }
	 
	 for(int i=0;i<n;i++)
	 {
		
		 for(int j=L.get(i);j<L.get(i+1);j++)
		 {
			 res.add(I.get(j),res.get(I.get(j))+(C.get(j)*liste.get(i)));
			 
		 }
	 }
	 
	return res;
	 
 }
}
