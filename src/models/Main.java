package models;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;







public class Main {
	
	private static float mat[][];
	private static Map<Integer, List<Integer>> myMap = new HashMap<>();
	private static int x[] = new int[2];
	private static int y = x[0];
	private static MCI compresse = new MCI();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		mat = new float[][] { { 0, 3, 5, 8 }, { 1, 0, 2, 0 }, { 0, 0, 0, 0 }, { 0, 3, 0, 0 } };

		List<Integer> liste = new ArrayList<Integer>();
		myMap.put(y, liste);
		
		BufferedReader br = new BufferedReader(new FileReader("res/p2p-Gnutella.txt"));
		String buf;
		buf = br.readLine();
		String [] results;
		int j;
		
		while ((buf = br.readLine()) != null)
		{
			if(buf.charAt(0) == '#')
				continue;
			results = buf.split("\t");
			ajouter(Integer.parseInt(results[0]),Integer.parseInt(results[1]));
		}

		for (Integer s : myMap.keySet()) {
			System.out.print(s + " :");
			 for (Integer ss : myMap.get(s)) {
			 System.out.print(" " + ss);
			 }
			System.out.println("\n");
		}

		

		 remplirCLI();
		 

		 compresse.afficheCLI();
		 
		 List<Float> lis = new ArrayList<Float>();

			int max = compresse.maxI() + 1;

			lis.add(0, 1.f);

			for (int i = 1; i < max; i++) {
				lis.add(i, 0.f);
			}
		 
		 List<Float> res= compresse.prodTransCLI(lis);
		 for(int i=0;i<res.size();i++)
		 {
			 System.out.print(res.get(i)+"\t");
		 }
		
		 

	}
	
	public static void ajouter(Integer sommet) 
	{
		if (myMap.containsKey(sommet))
			return;
		myMap.put(sommet, new ArrayList<Integer>());
	}
	
	public static void ajouter(Integer de, Integer a) 
	{
		ajouter(de);
		ajouter(a);
		myMap.get(de).add(a);
	}

	public static void remplirCLI() {

		int ligne = 0;
		int contenu = 0;

		int parcours = 0;

		Set<java.util.Map.Entry<Integer, List<Integer>>> setHm = myMap.entrySet();

		Iterator<java.util.Map.Entry<Integer, List<Integer>>> it = setHm.iterator();
		compresse.getL().add(ligne);
		while (it.hasNext()) {

			java.util.Map.Entry<Integer, List<Integer>> e = it.next();
			if (e.getKey() == parcours) {

				for (int f = 0; f < e.getValue().size(); f++) {
					compresse.getC().add(((float) 1 / (float) (e.getValue().size())));
					contenu++;
					compresse.getI().add(e.getValue().get(f));
				}

				compresse.getL().add(contenu + 1);
				parcours++;

			}

			else if (e.getKey() != parcours) {
				compresse.getL().add(compresse.getL().get(compresse.getL().size() - 1));
				parcours++;
			}

		}

	}
}
