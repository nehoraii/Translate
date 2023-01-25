package org.example.logic;

import org.example.entity.MassageEntity;
import org.example.querys.QueryToMassageTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MassageLogic extends MassageEntity {
    List<MassageEntity>list=new ArrayList<MassageEntity>();
    @Override
    public void setMassage(String massage) {
        MassageEntity massageO=new MassageEntity();
        massageO.setMassage(massage);
        ResultSet resultSet=null;
        if (!list.contains(massageO)){
            QueryToMassageTable query=new QueryToMassageTable();
            resultSet=query.select(massage);
            if(resultSet==null){
                query.Insert(massage);
                list.add(massageO);
                System.out.println("The massage added to DB");
                return;
            }
        }
        System.out.println("This massage already in system");
    }
}
