package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import it.polito.tdp.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class SpellCheckerController {
	

	
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextArea textArea1;

	    @FXML
	    private Button spellCheck;

	    @FXML
	    private TextArea textArea2;


	    @FXML
	    private Text textErrori;
	    @FXML
	    private Button clear;

	    @FXML
	    private ChoiceBox<String> language;

	    @FXML
	    private Text textTempo;

		private Model model;

	    @FXML
	    void doClear(ActionEvent event) {
	    	
	    	textArea1.clear();
	    	textArea2.clear();
	    	textTempo.setText("");
	    	textErrori.setText("");

	    }

	    @FXML
	    void doSpellCheck(ActionEvent event) {
	    	
	    	if(language.getValue()==null) {
	    		textArea2.setText("Selezionare una lingua");
	    		return;
	    	}
	    	
	    	
	    	String stringa = language.getValue();
	    	model.loadDictionary(stringa);
	    	String testo[]= textArea1.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").split(" ");
	    	LinkedList<String> parole = new LinkedList<String>();
	    	int numero=0;
	    	
	    	List<RichWord> richword ;
	    	for(int i=0; i< testo.length; i++)
	    	{
	    		parole.add(testo[i]);
	    	}
	    	long start = System.nanoTime();
	    	
	    	//richword =model.spellCheckText(parole);
	    	
	    	richword =model.spellCheckTextDicotomico(parole);
	    	
	    	//ricerca LINEARE
	    	//richword =model.spellCheckTextLineare(parole);
	    	
	    	long end = System.nanoTime();
	    	textTempo.setText(end+"");
	    	stringa="";
	    	for(RichWord s: richword)
	    		if(!s.isCorretta())
	    			{
	    			stringa+= s;
	    			numero++;
	    			}
	    	textArea2.setText(stringa);
	    	textErrori.setText("Sono state trovate "+numero+" errate");
	    }
	    

	    @FXML
	    void initialize() {
	        assert textArea1 != null : "fx:id=\"textArea1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert spellCheck != null : "fx:id=\"spellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textArea2 != null : "fx:id=\"textArea2\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textErrori != null : "fx:id=\"textErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert language != null : "fx:id=\"language\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert textTempo != null : "fx:id=\"textTempo\" was not injected: check your FXML file 'SpellChecker.fxml'.";

	    }

		public void setModel(Model model) {
			// TODO Auto-generated method stub
			this.model = model;
			language.getItems().add("English");
			language.getItems().add("Italian");
			
		}

		
	}
	

