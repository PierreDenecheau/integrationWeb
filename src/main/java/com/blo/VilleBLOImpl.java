package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;
	
	@Override
	public ArrayList<Ville> getInfoVilles(String codePostal) throws VilleException {
		ArrayList<Ville> listVille;
		
        if (codePostal == null || "".equalsIgnoreCase(codePostal)) {
			listVille = villeDAO.findVilles();
		} else {
			listVille = null;
		}
        
        System.out.println("Nombre de ville récupérée(s) : " + listVille.size() );
		
		return listVille;
	}

}
