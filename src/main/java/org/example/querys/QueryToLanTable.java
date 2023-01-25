package org.example.querys;

import java.sql.*;

public class QueryToLanTable {
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
    public void Insert(String LanguageName){
        String query="INSERT INTO languages (language_name)\n" +
                "\tVALUES(?);";

        try {
            Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,LanguageName);
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

        String query="SELECT " +stringColumns+" FROM languages"+
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
                    System.out.print("Language Name:   "+resultSet.getString("language_name")+"\n");
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

        String query="SELECT " +stringColumns+ " FROM languages";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:   "+resultSet.getInt("id"));
                    System.out.print("Language Name:   "+resultSet.getString("language_name")+"\n");
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
        String query="UPDATE languages\n"+
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
    public ResultSet select(int ConditionId,String condition,String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM languages \n"+
                "\t WHERE "+condition+" LIKE ? ";
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
    public ResultSet select(String Condition,String condition,String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM languages \n"+
                "\t WHERE " +condition+ " LIKE ? ";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,Condition);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                return resultSet;

            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            return null;

        }

    }
    public ResultSet select(String LanguageName) {
        Connection connection=getConnection();

        String query="SELECT * FROM languages \n"+
                "\t WHERE language_name LIKE ? ";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,LanguageName);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                return resultSet;

            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            return null;

        }

    }
    public ResultSet select(int ConditionID) {
        Connection connection=getConnection();

        String query="SELECT language_name FROM languages \n"+
                "\t WHERE id = ? ";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionID);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
            return null;

        }

    }
    public ResultSet select(String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM languages";
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
