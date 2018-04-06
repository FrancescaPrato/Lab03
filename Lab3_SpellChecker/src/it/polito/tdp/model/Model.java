package it.polito.tdp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Model {
	List<String> dizionario = new LinkedList<String>();
	List<String> dizionarioInglese = new LinkedList<String>();
	List<String> dizionarioItaliano = new LinkedList<String>();
	
	

	public Model() {
		// TODO Auto-generated constructor stub
		try { 
		 	FileReader fr = new FileReader("rsc/English.txt"); 
		 	BufferedReader br = new BufferedReader(fr); 
		 	 	String word; 
		 	 	while ((word = br.readLine()) != null) { 
		 	 		dizionarioInglese.add(word.toLowerCase());	 	 
		 	 	} 
		 	 	br.close(); 
	 	 	 	 
		 	 } catch (IOException e){ 
	 	 	System.out.println("Errore nella lettura del file");  } 
		try { 
		 	FileReader fr = new FileReader("rsc/Italian.txt"); 
		 	BufferedReader br = new BufferedReader(fr); 
		 	 	String word; 
		 	 	while ((word = br.readLine()) != null) { 
		 	 		dizionarioItaliano.add(word.toLowerCase());
			 	 
		 	 	} 
		 	 	br.close(); 
	 	 	 	 
		 	 } catch (IOException e){ 
	 	 	System.out.println("Errore nella lettura del file");  } 

	}

	
	public void loadDictionary(String language)  {
		
		if(language.compareTo("English")==0)
			{
			dizionario= dizionarioInglese;
			}
		else
			if(language.compareTo("Italian")==0)
			{
			dizionario= dizionarioItaliano;
			}
			
		}
	
	  public List<RichWord> spellCheckText(List<String> inputTextList) {
		  
		  List<RichWord> listaParole = new LinkedList<RichWord>();
			 
		  for(String s: inputTextList)
		  {
			  if(dizionario.contains(s))
				  listaParole.add(new RichWord(s,true));
			  else
				  listaParole.add(new RichWord(s,false));
		  
		  }
		  return listaParole;
	  }
	  
	  public List<RichWord> spellCheckTextLineare(List<String> inputTextList) {
		  List<RichWord> listaParole = new LinkedList<RichWord>();
				 
		  for(String s: inputTextList)
		  {
			  if(ricercaLineare(s.toLowerCase()))
				  listaParole.add(new RichWord(s,true));
			  else
				  listaParole.add(new RichWord(s,false));
		  
		  }
		  return listaParole;
	  }
	  public List<RichWord> spellCheckTextDicotomico(List<String> inputTextList) {
		  List<RichWord> listaParole = new LinkedList<RichWord>();
				 
		  for(String s: inputTextList)
		  {
			  if(dicotomica(s.toLowerCase()))
				  listaParole.add(new RichWord(s,true));
			  else
				  listaParole.add(new RichWord(s,false));
		  
		  }
		  return listaParole;
	  }
	 
	  public boolean ricercaLineare  (String s) {
		  System.out.println("size: "+dizionario.size());
		  for(int i=0; i<dizionario.size()-1;i++) {
			  if(dizionario.get(i).compareTo(s)==0)
				  return true;
			  System.out.println(dizionario.get(i));
			  System.out.println(i);
		  }
		  return false;
		  }
	  
	  public boolean dicotomica  (String s) {
		  if(dizionario.size()<1)
			  return false;
		  int max=(dizionario.size()-1);
		  int min=0;
		 int i=max/2;
		 while(i>0 && i!= dizionario.size()-1)
		 	{
			 if(dizionario.get(i).compareTo(s)==0)
		 		return true;
			 if(dizionario.get(i).compareTo(s)<0)
			 		{
				 	min=i;
				 	i= i+(max-i)/2;
				 	}
			 if(dizionario.get(i).compareTo(s)>0)
			 		{
				 	max=i;
				 	i= (i-min)/2;
				 	
			 		}
			 		 			
		 	}
		  
		  return false;
		  
	  }
}
