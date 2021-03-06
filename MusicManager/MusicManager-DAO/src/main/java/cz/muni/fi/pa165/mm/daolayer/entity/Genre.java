package cz.muni.fi.pa165.mm.daolayer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This java class represents the Genre entity
 * @author Yehor Safonov; 487596
 */

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String name;

    private String description;

    public  Genre(){}

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return "ID: " + this.getId() + " Name: " + this.getName() + " Description: " + this.getDescription();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Genre))
            return false;
        Genre genre = (Genre) obj;
        if(!id.equals(genre.getId()))
            return false;
        if(!name.equals(genre.getName()))
            return false;
        if (!description.equals(genre.getDescription()))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }
}