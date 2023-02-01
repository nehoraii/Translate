package org.example.logic;

import org.example.entity.LanguageEntity;
import org.example.querys.QueryToLanTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LanguageLogic extends LanguageEntity {
        List<LanguageEntity> listLanguage=new ArrayList<LanguageEntity>();
        @Override
        public void setLanguage(String language) {
                QueryToLanTable query=new QueryToLanTable();
                        LanguageEntity lunguageO=new LanguageEntity();
                        lunguageO.setLanguage(language);
                if(!listLanguage.contains(lunguageO)){//בדיקה האם נמצא ברשימה במידה וכן אז אין למה לבדוק אםהוא נמצא בdb
                        ResultSet resultSet=null;
                        resultSet=query.select(language);
                        if(resultSet==null) {//אם לא נמצא ברשימה נבדוק אם נמצא ב-db
                                LanguageEntity languageObject = new LanguageEntity();
                                languageObject.setLanguage(language);
                                listLanguage.add(lunguageO);
                                query.Insert(languageObject.getLanguage());
                                System.out.println("The language added to the system successfully");
                                return;
                        }
                }
                System.out.println("The language is in the DB");

        }


}
