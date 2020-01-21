package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findVilles();

	public ArrayList<Ville> findVilleByCodePostal(String codePostal);

	public void insertVille(Ville ville);
	
	public void updateVille(String codePostal, Ville ville);
	
}
