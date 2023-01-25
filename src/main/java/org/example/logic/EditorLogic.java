package org.example.logic;

import org.example.entity.EditorEntity;
import org.example.querys.QueryToEditorTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorLogic extends EditorEntity {

    List<EditorEntity> listNameEditor=new ArrayList<EditorEntity>();

    public void setEditor(String name,String Phone,String Email) throws SQLException {
        EditorEntity editor=new EditorEntity();
        editor.setName(name);
        editor.setPhone(Phone);
        editor.setEmail(Email);
        if(!listNameEditor.contains(editor)){
            QueryToEditorTable query=new QueryToEditorTable();
            ResultSet resultSet =null;
            resultSet=query.select(name,Phone,Email);
            if (resultSet==null) {
                query.Insert(name,Phone,Email);
                System.out.println("The editor add to the system successfully");
                listNameEditor.add(editor);
                return;
            }
        }
        System.out.println("The editor has been the system");
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
