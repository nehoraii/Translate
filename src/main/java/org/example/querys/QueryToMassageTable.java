package org.example.querys;

import java.sql.*;

public class QueryToMassageTable {
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
    public void Insert(String TheMassage){
        String query="INSERT INTO massage (massage)" +
                "VALUES(?);";

        try {
            Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,TheMassage);
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

        String query="SELECT " +stringColumns+" FROM massage"+
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
                    System.out.print("  The Massage:   "+resultSet.getString("massage")+"\n");
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

        String query="SELECT " +stringColumns+ " FROM massage";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            if(arrColums[0].contains("*"))
            {
                while (resultSet.next()) {
                    System.out.print("ID:   "+resultSet.getInt("id"));
                    System.out.print("  The Massage:   "+resultSet.getString("massage")+"\n");
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
        String query="UPDATE massage\n"+
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
    public ResultSet select(int ConditionId,String arrColumns[]) {
        Connection connection=getConnection();
        String stringColumns=arrayConsolidation(arrColumns);

        String query="SELECT " +stringColumns+ " FROM massage \n"+
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

        String query="SELECT " +stringColumns+ " FROM massage";
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

    public ResultSet select(String Massage) {
        Connection connection=getConnection();

        String query="SELECT * FROM massage \n"+
                "\t WHERE massage = ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,Massage);
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
    public ResultSet select(int Massage) {
        Connection connection=getConnection();

        String query="SELECT * FROM massage \n"+
                "\t WHERE id = ?";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,Massage);
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
