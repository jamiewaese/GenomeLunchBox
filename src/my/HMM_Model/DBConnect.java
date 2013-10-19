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
    
}