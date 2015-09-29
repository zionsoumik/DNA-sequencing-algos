package needleman;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Soumik
 */
public class getdata {
    Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
        public ArrayList fromdb() throws SQLException{
            String sql="select * from sequence;";
            ArrayList arr=new ArrayList();
        try {
            mysqlconnector con=new mysqlconnector();
			cn=con.getConnection();
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                arr.add(rs.getString(1));
            }
           
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return arr;
        }
}
