/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fasta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Soumik
 */
public class putseq {
    Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
    public void intodb(int sc,String a,String b,double d1,double d2) throws SQLException{
       
        String sql1="insert into sequence1 values(?,?,?,?,?)";
      
             
        try{
            mysqlconnector con=new mysqlconnector();
			cn=con.getConnection();
		    ps=cn.prepareStatement(sql1);
           
               ps.setString(1, a);
               ps.setString(2, b);
               ps.setInt(3, sc);
               ps.setDouble(4, d1);
                ps.setDouble(5, d2);
               ps.executeUpdate();
           

                   
                    cn.commit();
                    cn.close();
        }catch(SQLException se)
{
	se.printStackTrace();
}
    }    
}
