package cz.muni.fi.pa165.musicmanager.backend.entity;

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
        if (obj == null)
            return false;
        if (getClass()!=obj.getClass())
            return false;
        Genre genre = (Genre) obj;
        if(id != genre.id)
            return false;
        if(!name.equals(genre.name))
            return false;
        if (description == null) {
            if (genre.description != null)
                return false;
        } else if (!description.equals(genre.description))
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