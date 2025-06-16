package com.vdk.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "specialty", schema = "healthdb", uniqueConstraints = {
        @UniqueConstraint(name = "name", columnNames = {"name"})
})
public class Specialty {
    @Id
    @Column(name = "specialty_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}