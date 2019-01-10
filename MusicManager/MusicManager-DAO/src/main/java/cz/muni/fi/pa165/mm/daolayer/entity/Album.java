package cz.muni.fi.pa165.mm.daolayer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Václav Stehlík; 487580
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDate releaseDate;

    @NotNull
    @ManyToOne
    private Performer performer;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs = new HashSet<>();

    public Album(@NotNull String name, @NotNull LocalDate releasDate, Set<Song> songs) {
        this.name = name;
        this.releaseDate = releasDate;
        this.songs = songs;
    }

    public Album() {
    }

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Song> getSongs() {
        return Collections.unmodifiableSet(songs);
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song item) {
        this.songs.add(item);
    }

    // TODO: TBA.
//    public void removeSong(Song item) {
//        this.songs.remove(item);
//    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Album)) {
            return false;
        }

        Album a = (Album) o;
        return this.getName().equals(a.getName()) && this.getReleaseDate().equals(a.getReleaseDate()) && this.getPerformer().equals(a.getPerformer());
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        result = prime * result + ((performer == null) ? 0 : performer.hashCode());
        return result;
    }
}
