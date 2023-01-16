package org.example;

import org.example.Connection.ConnectionToDB;
import org.example.Logic.LanguageLogic;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException{
        ConnectionToDB connection=new ConnectionToDB();
        //connection.getConnection("postgres","nehoraii0556652485");
        connection.Insert("nehoraii","05566524850","sfbsfn@gmail.com");
    }
}