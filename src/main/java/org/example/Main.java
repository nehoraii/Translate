package org.example;

import jakarta.persistence.Query;
import org.example.logic.*;
import org.example.querys.QueryToEditorTable;
import org.example.querys.QueryToLanTable;
import org.example.querys.QueryToMassageTable;
import org.example.querys.QueryToTranslateTable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author EYAL GOLAN
 */
public class Main {
    public static void main(String[] args) throws SQLException {

        LanguageLogic logic=new LanguageLogic();
        logic.setLanguage("Englis3");
        logic.setLanguage("Englis3");
        logic.setLanguage("Englis3");

        EditorLogic editorLogic=new EditorLogic();
        //editorLogic.setEditor("a","0556654","nehor");
        QueryToEditorTable e=new QueryToEditorTable();
        String[] cars={"*"};
        e.veiwDB(cars);

        QueryToLanTable queryLan=new QueryToLanTable();
        ResultSet resultSet=queryLan.select("Englis5");
        QueryToEditorTable queryEdi=new QueryToEditorTable();
        ResultSet resultSet1=queryEdi.select("a","055665","nehor");
        EditorLanguageLogic editorLanguageLogic=new EditorLanguageLogic();
        int IDEditor=resultSet1.getInt("id");
        int IDlan=resultSet.getInt("id");//לבדוק מה קורה כאשר ה-ID הוא null
        editorLanguageLogic.setEditorLanguage(IDlan, EditorLanguageLogic.LevelOfLanguage.MEDIUM,IDEditor);


        MassageLogic massageLogic=new MassageLogic();
        massageLogic.setMassage("Hello World");



        LocalDate date= LocalDate.now();
        TranslateLogic translate=new TranslateLogic();
        translate.setTranslate("Hello World","Englis5","a","055665", "qwertyuiop",Date.valueOf(date));//כאןןןןןןןןןןןןןןןןןן


    }
}