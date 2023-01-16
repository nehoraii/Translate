package org.example.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tranclate", schema = "public", catalog = "Translate")
public class TranclateEntity {
    private int id;
    private Integer massageId;
    private Integer languageId;
    private Integer editorId;
    private String tranclate;
    private Date data;

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
    @Column(name = "MassageID", nullable = true)
    public Integer getMassageId() {
        return massageId;
    }

    public void setMassageId(Integer massageId) {
        this.massageId = massageId;
    }

    @Basic
    @Column(name = "LanguageID", nullable = true)
    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "EditorID", nullable = true)
    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    @Basic
    @Column(name = "Tranclate", nullable = true, length = -1)
    public String getTranclate() {
        return tranclate;
    }

    public void setTranclate(String tranclate) {
        this.tranclate = tranclate;
    }

    @Basic
    @Column(name = "Data", nullable = true)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranclateEntity that = (TranclateEntity) o;
        return id == that.id && Objects.equals(massageId, that.massageId) && Objects.equals(languageId, that.languageId) && Objects.equals(editorId, that.editorId) && Objects.equals(tranclate, that.tranclate) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, massageId, languageId, editorId, tranclate, data);
    }
}
