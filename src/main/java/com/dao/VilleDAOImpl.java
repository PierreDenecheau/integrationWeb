package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfigurationSol1;
import com.dto.Coordonnees;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	@Override
	public ArrayList<Ville> findVilles() {
		ArrayList<Ville> listVille = new ArrayList<Ville>();

		try {
			Connection con = JDBCConfigurationSol1.getConnection();
	
			Statement statement = con.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ville_france");
			
			while(resultSet.next()){
				Ville ville = new Ville();
				
				ville.setCodeCommune(resultSet.getString("Code_commune_INSEE"));
				ville.setNomCommune(resultSet.getString("Nom_commune"));
				ville.setCodePostal(resultSet.getString("Code_postal"));
				ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
				ville.setLigne(resultSet.getString("Ligne_5"));
				Coordonnees coordonnees = new Coordonnees();
				coordonnees.setLatitude(resultSet.getString("Latitude"));
				coordonnees.setLongitude(resultSet.getString("Longitude"));
				ville.setCoordonnees(coordonnees);

				listVille.add(ville);
			}
			
            // close de la connexion
			resultSet.close();
			statement.close();
			    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return listVille;
            
    }

}
