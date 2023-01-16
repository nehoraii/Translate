package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "editor_languages", schema = "public", catalog = "Translate")
public class EditorLanguagesEntity {
    private int id;
    private int idLanguage;
    private int level;
    private int idEditor;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IDLanguage", nullable = false)
    public int getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(int idLanguage) {
        this.idLanguage = idLanguage;
    }

    @Basic
    @Column(name = "Level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "IDEditor", nullable = false)
    public int getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(int idEditor) {
        this.idEditor = idEditor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditorLanguagesEntity that = (EditorLanguagesEntity) o;
        return id == that.id && idLanguage == that.idLanguage && level == that.level && idEditor == that.idEditor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idLanguage, level, idEditor);
    }
}
