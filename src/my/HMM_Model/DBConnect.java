/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.HMM_Model;

/**
 *
 * @author anu
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.*;

public class DBConnect {
 
       private Connection con;
       private Statement queryStatement;
       private ResultSet resultSet;
       
       public DBConnect(String ip, String port, String password, String username, String db ,JLabel jLabel_ConnectToDBStatus,String connectionName,JComboBox jComboBox_RecentDBList){
           //Boolean connected= false;
           //String connectorStr= "jdbc:mysql://"+ip+":"+port+"/"+db;
           //System.out.println("CONSTR"+connectorStr);
           //System.out.println("IP: "+ip);
           try{
              
               Class.forName("com.mysql.jdbc.Driver");
               String connectorStr= "jdbc:mysql://"+ip+":"+port+"/"+db;
               con = DriverManager.getConnection(connectorStr,username,password);
               st = con.createStatement();
               //st = con.createStatement();
               //connected=true;
               jComboBox_RecentDBList.addItem(connectionName);
               jLabel_ConnectToDBStatus.setText("Connected");
           }catch(ClassNotFoundException | SQLException ex){
               System.out.println("Error"+ex);
                jLabel_ConnectToDBStatus.setText("Not Connected");
           }   
           //return connected;
       }
       
       public ResultSet getData(String query){
               
               try{
               //String query = "SELECT gender FROM CF.patient_information";
               
               resultSet = queryStatement.executeQuery(query);
               
               }catch(Exception ex){
               System.out.println("Error"+ex);
           }
         return resultSet;
        }
       
       
       
    
       public LinkedHashMap <String, ArrayList <String>> buildTaxonomyTree (DBConnect connect,String db){
          
           LinkedHashMap <String, ArrayList <String>> taxomonyTree = new LinkedHashMap <> ();
          
            try{
            String query = "SELECT taxonomy_id, kingdom_name, phylum_name, class_name, order_name, family_name, genus_name, species_name " +
                         "FROM "+db+".Taxonomy";
            ResultSet tfrs;
            tfrs=connect.getData(query);
            
                 while (tfrs.next()) {
                  ArrayList taxonomyList = new ArrayList ();
                 String taxid = tfrs.getString("taxonomy_id");
                 String kingdom = tfrs.getString("kingdom_name");
                 String phylum = tfrs.getString("phylum_name");
                 String classname = tfrs.getString("class_name");
                 String order = tfrs.getString("order_name");
                 String family = tfrs.getString("family_name");
                 String genus = tfrs.getString("genus_name");
                 String species = tfrs.getString("species_name");
                 //System.out.println("Column Name: "+columnName);               
                 taxonomyList.add(kingdom);
                 taxonomyList.add(phylum);
                 taxonomyList.add(classname);
                 taxonomyList.add(order);
                 taxonomyList.add(family);
                 taxonomyList.add(genus);
                 taxonomyList.add(species);
                 System.out.println("taxid"+taxid);
                 System.out.println("genus"+genus);
                 taxomonyTree.put(taxid,taxonomyList);
                 
                }
              }catch(Exception ex){
               System.out.println("Error"+ex);
             }   
           
           return taxomonyTree;
       }
       
       
       public ArrayList createSpeciesList (DBConnect connect,String db,String spiecesname){
   
           String query = "SELECT abbreviation_name FROM "+db+".OrganismInfo "+
                                 "WHERE species_name='"+spiecesname+"'";
                 ResultSet speciesrs;
                 ArrayList speciesList = new ArrayList ();
          try{
                 speciesrs=connect.getData(query);
                 
                 while (speciesrs.next()) {
                   String aname = speciesrs.getString("abbreviation_name");
                   speciesList.add(aname);
                    System.out.println("spiecesname"+spiecesname);
                 System.out.println("aname"+aname);
                    
                 }
          }
          catch(Exception ex){
               System.out.println("Error"+ex);
           }
               return speciesList;
           
           
    }    
       
       
       
       
       
}