package org.example.logic;

import org.example.entity.EditorLanguagesEntity;
import org.example.querys.QueryToEditorLanTable;
import org.example.querys.QueryToEditorTable;
import org.example.querys.QueryToLanTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditorLanguageLogic extends EditorLanguagesEntity {
        public enum LevelOfLanguage{VERYLOW,LOW,MEDIUM,HIGH,VERYHIGH};
        List<EditorLanguagesEntity> list =new ArrayList<EditorLanguagesEntity>();
        public void setEditorLanguage(int IdLanguage,LevelOfLanguage Level,int IdEditor){
                QueryToLanTable lanTable=new QueryToLanTable();
                ResultSet resultSet=null;
                resultSet=lanTable.select(IdLanguage);
                if(resultSet==null){
                        System.out.println("There is no such language in the DB");
                        return;
                }
                QueryToEditorTable editorTable=new QueryToEditorTable();
                resultSet=editorTable.select(IdEditor);
                if(resultSet==null) {
                        System.out.println("There is no such editor in the DB");
                        return;
                }
                EditorLanguagesEntity editorLanguages=new EditorLanguagesEntity();
                editorLanguages.setIdLanguage(IdLanguage);
                editorLanguages.setIdEditor(IdEditor);
                editorLanguages.setLevel(Level);
                if(!list.contains(editorLanguages)){
                        QueryToEditorLanTable query=new QueryToEditorLanTable();
                        resultSet=query.select(IdLanguage,IdEditor);
                        if(resultSet==null){
                                query.Insert(IdLanguage,Level,IdEditor);
                                System.out.println("the Language Editor added to DB");
                                list.add(editorLanguages);
                                return;
                        }
                        System.out.println("The editor appears in the list");
                }
        }
}
