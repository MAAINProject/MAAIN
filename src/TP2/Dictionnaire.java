package TP2;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Dictionnaire {
	private final String wikiURL = "https://fr.wiktionary.org/wiki/Utilisateur:Darkdadaah/Listes/Mots_dump/frwiki/2016-02-03";
	private final String dictFileName = "./res/WIKI.words";
	private final String XMLFileName = "./res/testwiki.xml";
	
	/**
	 * Require jsoup-1.10.2.jar
	 */
	public void getWikiWords()
	{
		try
		{
			Document doc = Jsoup.connect(wikiURL).get();
			Elements tableElements = doc.select("table");        
			Elements tableRowElements = tableElements.select(":not(thead) tr");
			Set<String> dictArray = new HashSet<String>();
			File dictWords = new File(dictFileName);
			FileWriter fw = new FileWriter(dictWords.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
	        
	        for (int i = 1; i < tableRowElements.size(); i++) 
	        {
	        	Element row = tableRowElements.get(i);
	        	
	        	if (row.select("td > a").text().toLowerCase().length() >= 5) 
	        	{
	        		dictArray.add(row.select("td > a").text().toLowerCase().replace("é", "e").replace("è", "e").replace("ê", "e").replace("à", "a").replace("â", "a"));
	        	}
	        }
	        
	        String[] dictArrayTemp = new String[dictArray.size()];
	        
	        int i = 0;
	        for (String word : dictArray) 
	        {
	        	dictArrayTemp[i] = word;
	        	i++;
			}
	                
	        dictArray.clear();
	        java.util.Arrays.sort(dictArrayTemp);
	        
	        for (int j = 0; j < dictArrayTemp.length; j++) 
	        {
	    		bw.write(dictArrayTemp[j]);
	    		bw.write("\n");
			}
	        
	        bw.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Require commons-lang3-3.5.jar 
	 */
	public Map<String, ArrayList<String>> wordPageRel()
	{
		Map<String, ArrayList<String>> wordPageMap = null;
	    try 
	    {
	    	wordPageMap = new HashMap<String, ArrayList<String>>();
	    	File XMLFile = new File(XMLFileName);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	org.w3c.dom.Document doc = dBuilder.parse(XMLFile);
	    	doc.getDocumentElement().normalize();
	    	
	    	for (String word : loadDict()) 
			{
				wordPageMap.put(word, new ArrayList<String>());
			}
	    	
	    	NodeList nList = doc.getElementsByTagName("page");

	    	for (int i = 0; i < nList.getLength(); i++) 
	    	{
	    		Node nNode = nList.item(i);
	    			    		
	    		if (nNode.getNodeType() == Node.ELEMENT_NODE)
	    		{
	    			org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
	    			String ID = eElement.getElementsByTagName("id").item(0).getTextContent();
	    			String unescapedText = StringEscapeUtils.unescapeHtml4(eElement.getElementsByTagName("text").item(0).getTextContent().toLowerCase().replace("é", "e").replace("è", "e").replace("ê", "e").replace("à", "a").replace("â", "a"));	    			
	    			for (String word : loadDict()) 
	    			{
	    				int j = 0;
	    				Pattern p = Pattern.compile(word);
	    				Matcher m = p.matcher(unescapedText);
	    				while (m.find())
	    				{
	    				    j++;
	    				}
	    				//System.out.println(word + " -> [" + ID + " -- " + j + "]");
	    				wordPageMap.get(word).add(ID +" - "+ j);
					}
	    		}
	    	}
	    	
	    	for (String word : wordPageMap.keySet()) 
	    	{
				for (String value : wordPageMap.get(word))
				{
					System.out.println("Word : " + word + " | Page : "+ value.split("-")[0] +"| Occurences :"+value.split("-")[1]);
				}
			}
	    				
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
		return wordPageMap;
	}
	
	private Set<String> loadDict()
	{
		Set<String> dictArray = null;
		try 
		{
			dictArray = new HashSet<String>();
			Scanner dictFileReader = new Scanner(new BufferedReader(new FileReader(dictFileName)));
			while (dictFileReader.hasNext()) 
			{
				dictArray.add(dictFileReader.nextLine());
			}
			dictFileReader.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return dictArray;
	}
}
