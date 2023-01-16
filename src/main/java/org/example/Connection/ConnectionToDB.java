package org.example.Connection;

import javax.xml.transform.Result;
import java.sql.*;

public class ConnectionToDB {
    public Connection getConnection(String user,String password){
        String url="jdbc:postgresql://localhost:5432/Translate";
        //String user="postgres";
        //String password="nehoraii0556652485";
        try {
            Connection connection= DriverManager.getConnection(url,user,password);
            System.out.println("The connection Succeeded");
            //connection.close();
            return connection;
        } catch (SQLException e) {
            System.out.println("The connection Fails");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public void Insert(String NameEditor,String PhoneEditor,String EmailEditor){
        Connection connection=getConnection("postgres","nehoraii0556652485");
        String query="INSERT INTO editor(namee,phone,email)"+
                "VALUES('"+NameEditor+"','"+PhoneEditor+"','"+EmailEditor+"')";

        try {
            Statement statement=connection.createStatement();
            int result=statement.executeUpdate(query);//update
            System.out.println("The Query Succeeded");

        } catch (SQLException e) {
            System.out.println("Fails");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
