package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {
	
	Set<String> corretti;
	Set<String> errati;
	String originale;
	AnagrammaDAO dao=new AnagrammaDAO();
	
	public void trovaAnagrammi(String s) {
		corretti=new HashSet<String>();
		errati= new HashSet<String>();
		originale=s;
		List<Integer> inseriti=new ArrayList<Integer>();
		String parziale=new String();
		faiRicorsione(parziale,inseriti,0);
	}
	
	private void faiRicorsione(String parziale, List<Integer> inseriti,Integer L) {
		if(L==originale.length() && esiste(parziale)) {
			corretti.add(parziale);
			return;
		}
		
		if(L==originale.length() && esiste(parziale)==false) {
			errati.add(parziale);
			return;
		}
		
		for(int i=0;i<originale.length();i++) {
			if(inseriti.contains(i)==false) {
				inseriti.add(i);
				
				String n=parziale+originale.charAt(i);
				faiRicorsione(n,inseriti,L+1);
				
				
				inseriti.remove(inseriti.size()-1);
			}
		}
	}
	
	public boolean esiste(String s) {
		return dao.isCorrect(s);
	}
	
	public Set<String> getCorretti(){
		return corretti;
	}
	
	public Set<String> getErrati(){
		return errati;
	}
}
