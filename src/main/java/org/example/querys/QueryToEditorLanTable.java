package org.example.querys;

import org.example.logic.EditorLanguageLogic;

import java.sql.*;

public class QueryToEditorLanTable {
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
            return "*";
        }
        String oneArr;
        oneArr = str[0];
        for (int i = 1; i < str.length; i++) {
            oneArr+= ", "+ str[i];
        }
        return  oneArr;
    }
    public void Insert(int IdLanguage , EditorLanguageLogic.LevelOfLanguage LevelLanguage, int IdEditor){
        String query="INSERT INTO editor_languages (id_language,level_language,id_editor)" +
                "VALUES(?,?,?);";

        try {
            Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,IdLanguage);
            statement.setString(2, LevelLanguage.name());
            statement.setInt(3,IdEditor);
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

        String query="SELECT " +stringColumns+" FROM editor_languages"+
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
                    System.out.print("ID Language:   "+resultSet.getInt("id_language"));
                    System.out.print("Level Language:   "+resultSet.getInt("level_language"));
                    System.out.print("ID Editor:   "+resultSet.getInt("id_editor"));
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

        String query="SELECT " +stringColumns+ " FROM editor_languages";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:  " + resultSet.getInt("id"));
                    System.out.print("  ID Language:  " + resultSet.getString("id_language"));
                    System.out.print("  Level Language:  " + resultSet.getString("level_language"));
                    System.out.print("  ID Editor:  " + resultSet.getString("id_editor") + "\n");
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
    public void update(String Column,int Data,int ConditionID){

        PreparedStatement statement=null;
        String query="UPDATE editor_languages\n"+
                "\tSET "+Column+"= ? \n"
                +"\tWHERE id= ?";
        try {
            Connection connection=getConnection();
            statement= connection.prepareStatement(query);
            statement.setInt(1,Data);
            statement.setInt(2,ConditionID);
            System.out.println("updated");
            statement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error Update");
            e.printStackTrace();
            throw new RuntimeException(e);
        }



    }
    public void update(String Column,String Data,int ConditionID){

        PreparedStatement statement=null;
        String query="UPDATE editor_languages\n"+
                "\tSET "+Column+"= ? \n"
                +"\tWHERE id= ?";
        try {
            Connection connection=getConnection();
            statement= connection.prepareStatement(query);
            statement.setString(1,Data);
            statement.setInt(2,ConditionID);
            System.out.println("updated");
            statement.executeUpdate();

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

        String query="SELECT " +stringColumns+ " FROM editor_languages \n"+
                "\t WHERE id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionId);
            ResultSet resultSet=statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;

        }

    }
    public ResultSet select(int ConditionIdLan,int ConditionIdEdi) {
        Connection connection=getConnection();

        String query="SELECT * FROM editor_languages \n"+
                "\t WHERE id_language = ? AND id_editor = ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionIdLan);
            statement.setInt(2,ConditionIdEdi);
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
    public ResultSet select(String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM editor_languages";
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
}
