package org.example.logic;

import org.example.entity.TranclateEntity;
import org.example.querys.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TranslateLogic extends TranclateEntity {
    List<TranclateEntity> list=new ArrayList<TranclateEntity>();
    public void setTranslate(int MassageID, int LanguageID, int EditorID, String Translate, Date date ){
        QueryToMassageTable queryMass=new QueryToMassageTable();
        ResultSet resultSet=null;//לשנות שישאל מהטבלה שלו
        resultSet=queryMass.select(MassageID);
        if(resultSet==null){
            System.out.println("The Massage is not found");
            return;
        }
        QueryToLanTable queryLan=new QueryToLanTable();
        resultSet=queryLan.select(LanguageID);
        if(resultSet==null){
            System.out.println("The Language is not found");
            return;
        }
        QueryToEditorTable queryEditor=new QueryToEditorTable();
        resultSet=queryEditor.select(EditorID);
        if(resultSet==null){
            System.out.println("The Editor is not found");
            return;
        }
        QueryToTranslateTable queryTranslate=new QueryToTranslateTable();
        TranclateEntity translate=new TranclateEntity();
        translate.setEditorId(EditorID);
        translate.setTranclate(Translate);
        translate.setLanguageId(LanguageID);
        translate.setData(date);
        translate.setMassageId(MassageID);
        if(!list.contains(translate)){
            resultSet=queryTranslate.select(MassageID,LanguageID);
            if (resultSet==null){
                queryTranslate.Insert(MassageID,LanguageID,EditorID,Translate,date);
                System.out.println("The translate added to DB");
                list.add(translate);
                return;
            }
        }
        System.out.println("The translate already in DB");
    }
    public void setTranslate(String Massage, String Language, String NameEditor,String PhoneEditor, String Translate, Date date ) throws SQLException {
        QueryToMassageTable queryMass=new QueryToMassageTable();
        ResultSet resultSet=null;//לשנות שישאל מהטבלה שלו
        resultSet=queryMass.select(Massage);
        if(resultSet==null){
            System.out.println("The Massage is not found");
            return;
        }
        QueryToLanTable queryLan=new QueryToLanTable();
        resultSet=queryLan.select(Language);
        if(resultSet==null){
            System.out.println("The Language is not found");
            return;
        }
        QueryToEditorTable queryEditor=new QueryToEditorTable();
        resultSet=queryEditor.select(NameEditor,PhoneEditor);
        if(resultSet==null){
            System.out.println("The Editor is not found");
            return;
        }
        QueryToTranslateTable queryTranslate=new QueryToTranslateTable();
        TranclateEntity translate=new TranclateEntity();
        translate.setEditorId(queryEditor.select(NameEditor,PhoneEditor).getInt("id"));
        translate.setTranclate(Translate);
        translate.setLanguageId(queryLan.select(Language).getInt("id"));
        translate.setData(date);
        translate.setMassageId(queryMass.select(Massage).getInt("id"));
        if(!list.contains(translate)){
            resultSet=queryTranslate.select(queryMass.select(Massage).getInt("id"),queryLan.select(Language).getInt("id"));
            if (resultSet==null){
                queryTranslate.Insert(queryMass.select(Massage).getInt("id"),queryLan.select(Language).getInt("id"),queryEditor.select(NameEditor,PhoneEditor).getInt("id"),Translate,date);
                System.out.println("The translate added to DB");
                list.add(translate);
                return;
            }
        }
        System.out.println("The translate already in DB");
    }
}
