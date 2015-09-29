package needleman;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;





/**
 *
 * @author Soumik
 */
public class mysqlconnector {
    Connection cn=null;

public Connection getConnection()
{
	try
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/needleman","root","evilkai4");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return cn;
}

}
