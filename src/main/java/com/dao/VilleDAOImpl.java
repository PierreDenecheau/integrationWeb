package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			
			resultSet.close();
			statement.close();
			con.close();
			    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return listVille;
            
    }
	
	@Override
	public ArrayList<Ville> findVilleByCodePostal(String codePostal) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();

		try {
			Connection con = JDBCConfigurationSol1.getConnection();
		
			PreparedStatement statement = con.prepareStatement("SELECT * FROM ville_france where Code_postal = ?");
			statement.setString(1, codePostal);
			
            ResultSet resultSet = statement.executeQuery();

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
			
			resultSet.close();
			statement.close();
			    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return listVille;
            
    }
	
	@Override
    public void insertVille(Ville ville) {

		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			
			String insertTableSQL = "INSERT INTO ville_france "
					+ "(Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) "
					+ "VALUES (?,?,?,?,?,?,?) ";
			
			
			PreparedStatement statement = con.prepareStatement(insertTableSQL);
			statement.setString(1, ville.getCodeCommune());
			statement.setString(2, ville.getNomCommune());
			statement.setString(3, ville.getCodePostal());
			statement.setString(4, ville.getLibelleAcheminement());
			statement.setString(5, ville.getLigne());
			statement.setString(6, ville.getCoordonnees().getLatitude());
			statement.setString(7, ville.getCoordonnees().getLongitude());
			
            statement.executeUpdate();
            
			statement.close();
			    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
            
    }
	
	@Override
    public void updateVille(String codePostal, Ville ville) {

		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			
			String updateTableSQL = "UPDATE ville_france SET "
					+ "Code_commune_INSEE = ?, Nom_commune = ?, Code_postal = ?, Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? "
					+ "WHERE Code_postal = ? ";
			
			
			PreparedStatement statement = con.prepareStatement(updateTableSQL);
			statement.setString(1, ville.getCodeCommune());
			statement.setString(2, ville.getNomCommune());
			statement.setString(3, codePostal);
			statement.setString(4, ville.getLibelleAcheminement());
			statement.setString(5, ville.getLigne());
			statement.setString(6, ville.getCoordonnees().getLatitude());
			statement.setString(7, ville.getCoordonnees().getLongitude());
			statement.setString(8, codePostal);
		
            statement.executeUpdate();
            
			statement.close();
			    
		} catch (SQLException e) {
		    e.printStackTrace();
		}
            
    }

}
