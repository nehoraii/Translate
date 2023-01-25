package org.example.querys;

import java.sql.*;
import java.util.Date;

public class QueryToTranslateTable {
    String url="jdbc:postgresql://localhost:5432/Translate";;
    String user="postgres";
    String password="nehoraii0556652485";
    private Connection getConnection(){
        try {
            Connection connection= DriverManager.getConnection(url,user,password);
            return connection;
        } catch (SQLException e) {
            System.out.println("The connection Fails");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    private String arrayConsolidation(String [] str){
        if(str==null){
            return "null";
        }
        String oneArr;
        oneArr = str[0];
        for (int i = 1; i < str.length; i++) {
            oneArr+= ", "+ str[i];
        }
        return  oneArr;
    }
    public void Insert(int MassageId, int LanguageId, int EditorId, String Translate, Date date){
        String query="INSERT INTO translate (massage_id,language_id,editor_id,the_translate,the_date)" +
                "VALUES(?,?,?,?,?);";

        try {
            Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,MassageId);
            statement.setInt(2,LanguageId);
            statement.setInt(3,EditorId);
            statement.setString(4,Translate);
            statement.setDate(5, (java.sql.Date) date);
            statement.executeUpdate();
            System.out.println("The Query Succeeded");
            connection.close();

        } catch (SQLException e) {
            System.out.println("Fails");
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    public void veiwDB(String[] arrColums,int ConditionId){
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColums);

        String query="SELECT " +stringColumns+" FROM translate"+
                "\n\tWHERE id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionId);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next())
                {
                    System.out.print("ID:   "+resultSet.getInt("id"));
                    System.out.print("  The Massage ID:   "+resultSet.getInt("massage_id"));
                    System.out.print("  The Language ID:   "+resultSet.getInt("language_id"));
                    System.out.print("  The Editor ID:   "+resultSet.getInt("editor_id"));
                    System.out.print("  The Translate:   "+resultSet.getString("the_translate"));
                    System.out.print("  The Massage ID:   "+resultSet.getDate("the_date")+"\n");
                }
                return;
            }
            while (resultSet.next()) {
                for (int i = 0; i < arrColums.length; i++) {
                    System.out.println(arrColums[i]+" :   "+resultSet.getString(arrColums[i]));
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void veiwDB(String[] arrColums){
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColums);

        String query="SELECT " +stringColumns+ " FROM translate";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:   "+resultSet.getInt("id"));
                    System.out.print("  The Massage ID:   "+resultSet.getInt("massage_id"));
                    System.out.print("  The Language ID:   "+resultSet.getInt("language_id"));
                    System.out.print("  The Editor ID:   "+resultSet.getInt("editor_id"));
                    System.out.print("  The Translate:   "+resultSet.getString("the_translate"));
                    System.out.print("  The Massage ID:   "+resultSet.getDate("the_date")+"\n");
                }
                return;
            }
            while (resultSet.next()) {
                for (int i = 0; i < arrColums.length; i++) {
                    System.out.println(arrColums[i]+" :   "+resultSet.getString(arrColums[i]));
                }
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void update(String Column,String Data,int ConditionID){

        PreparedStatement statement=null;
        String query="UPDATE translate\n"+
                "\tSET "+Column+"= ? \n"
                +"\tWHERE id= ?";
        try {
            Connection connection=getConnection();
            statement= connection.prepareStatement(query);
            statement.setString(1,Data);
            statement.setInt(2,ConditionID);
            statement.executeUpdate();
            System.out.println("updated");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error Update");
            e.printStackTrace();
            throw new RuntimeException(e);
        }



    }
    public void update(String Column,int Data,int ConditionID){

        PreparedStatement statement=null;
        String query="UPDATE translate\n"+
                "\tSET "+Column+"= ? \n"
                +"\tWHERE id= ?";
        try {
            Connection connection=getConnection();
            statement= connection.prepareStatement(query);
            statement.setInt(1,Data);
            statement.setInt(2,ConditionID);
            statement.executeUpdate();
            System.out.println("updated");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error Update");
            e.printStackTrace();
            throw new RuntimeException(e);
        }



    }
    public ResultSet select(int ConditionId,String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM translate \n"+
                "\t WHERE id= ? ";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionId);
            ResultSet resultSet=statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            return null;

        }

    }
    public ResultSet select(String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM translate";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }
    public ResultSet select(int MassageID, int LanguageID) {
        Connection connection=getConnection();

        String query="SELECT * FROM translate \n"
                +"\t WHERE massage_id = ? AND language_id = ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,MassageID);
            statement.setInt(2,LanguageID);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }
    public ResultSet select(int DataID, String ConditionID) {
        Connection connection=getConnection();

        String query="SELECT * FROM translate \n"
                +"\t WHERE "+ConditionID+"= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,DataID);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
    }
}
