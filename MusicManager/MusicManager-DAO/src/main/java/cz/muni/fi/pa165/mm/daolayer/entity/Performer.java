package cz.muni.fi.pa165.mm.daolayer.entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marek Barinka; 456295
 */
@Entity
public class Performer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    /**
     * Date when performer start creating songs
     */
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private String country;
    
    @OneToMany(mappedBy = "performer")
    private Set<Album> albums = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }

    public void addAlbum(Album a) {
        a.setPerformer(this);
        this.albums.add(a);
    }
    
    public void removeAlbum(Album a) {
        boolean removed = this.albums.remove(a); 
        if (removed) {
            a.setPerformer(null);
        }
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (this.getId() == null) {
            return false;
        }
        if (!(other instanceof Performer)) {
            return false;
        }
        Performer p = (Performer) other;
        return this.getId().equals(p.getId());
    }
    
    @Override
    public int hashCode() {
        int result = 31;
        if (this.getId() == null) {
            return result;
        }
        return result * this.getId().hashCode();
    }
}