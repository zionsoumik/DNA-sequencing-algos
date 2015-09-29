package fasta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Soumik
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class putdata {
    Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
    public void intodb() throws SQLException{
       
        String sql1="insert into sequence values(?)";
        String alphabet="ATGC";
                Random r=new Random();
                int random1=r.nextInt(4);
                 
                 ArrayList a=new ArrayList();
                 
                
              for (int i=0;i<100;i++){
                  String f=alphabet.substring(random1,random1+1);  
               for(int j=1;j<64;j++){
                   f=f+alphabet.charAt(r.nextInt(alphabet.length()));
               }
               a.add(f);
              }
             
        try{
            mysqlconnector con=new mysqlconnector();
			cn=con.getConnection();
		    ps=cn.prepareStatement(sql1);
           for (int j=0;j<a.size();j++) {
               ps.setString(1, (String) a.get(j));
               ps.executeUpdate();
           }

                   
                    cn.commit();
        }catch(SQLException se)
{
	se.printStackTrace();
}
    }    
    }

