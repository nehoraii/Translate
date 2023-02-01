package org.example.querys;

import java.sql.*;

public class QueryToEditorTable {
    String url="jdbc:postgresql://localhost:5432/Translate";;
    String user="postgres";
    String password="nehoraii0556652485";
    private String arrayConsolidation(String [] str){
        String oneArr="";
        if(str==null){
            oneArr+="*";
            return oneArr;
        }
        oneArr = str[0];
        for (int i = 1; i < str.length; i++) {
            oneArr+= ", "+ str[i];
        }
        return  oneArr;
    }

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
    public void Insert(String NameEditor,String PhoneEditor,String EmailEditor){
        String query="INSERT INTO editor (namee,phone,email)" +
                "VALUES(?,?,?);";

        try {
        Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,NameEditor);
        statement.setString(2,PhoneEditor);
        statement.setString(3,EmailEditor);
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

        String query="SELECT " +stringColumns+" FROM editor\n"+
                "\tWHERE id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionId);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:  " + resultSet.getInt("id"));
                    System.out.print("  Name:  " + resultSet.getString("namee"));
                    System.out.print("  Phone:  " + resultSet.getString("phone"));
                    System.out.print("  Email:  " + resultSet.getString("Email") + "\n");
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

        String query="SELECT " +stringColumns+ " FROM editor";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:  " + resultSet.getInt("id"));
                    System.out.print("  Name:  " + resultSet.getString("namee"));
                    System.out.print("  Phone:  " + resultSet.getString("phone"));
                    System.out.print("  Email:  " + resultSet.getString("Email") + "\n");
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
        String query="UPDATE editor\n"+
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

        String query="SELECT " +stringColumns+ " FROM editor \n"+
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
    public ResultSet select(String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM editor";
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
    public ResultSet select(String ConditionStr,String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM editor \n"+
                "\t WHERE id= ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,ConditionStr);
            ResultSet resultSet=statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;

        }

    }
    public ResultSet select(String Name,String Phone,String Email) {
        Connection connection=getConnection();
        ResultSet resultSet=null;
        String query="SELECT * FROM editor \n"+
                "\t WHERE namee = ? AND phone= ? AND email=?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,Name);
            statement.setString(2,Phone);
            statement.setString(3,Email);
            resultSet =statement.executeQuery();
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
    public ResultSet select(int ConditionID) {
        Connection connection=getConnection();
        ResultSet resultSet=null;
        String query="SELECT * FROM editor \n"+
                "\t WHERE id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ConditionID);
            resultSet =statement.executeQuery();
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
    public ResultSet select(String Name,String Phone) {
        Connection connection=getConnection();
        ResultSet resultSet=null;
        String query="SELECT * FROM editor \n"+
                "\t WHERE namee = ? AND phone = ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,Name);
            statement.setString(2,Phone);
            resultSet =statement.executeQuery();
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
