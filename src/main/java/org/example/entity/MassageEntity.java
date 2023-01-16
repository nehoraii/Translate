package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "massage", schema = "public", catalog = "Translate")
public class MassageEntity {
    private int id;
    private String massage;

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
    @Column(name = "Massage", nullable = false, length = -1)
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MassageEntity that = (MassageEntity) o;
        return id == that.id && Objects.equals(massage, that.massage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, massage);
    }
}
