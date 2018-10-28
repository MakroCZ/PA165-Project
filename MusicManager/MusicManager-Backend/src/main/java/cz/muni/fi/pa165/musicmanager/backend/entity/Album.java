package cz.muni.fi.pa165.musicmanager.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private LocalDate date;

    @ManyToOne
    private Performer performer;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs = new HashSet<>();

    public Album(@NotNull String name, @NotNull LocalDate date, Set<Song> songs) {
        this.name = name;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song item) {
        this.songs.add(item);
    }

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
            return true;
        }
        if (!(o instanceof Album)) {
            return false;
        }

        Album a = (Album) o;
        return this.getId().equals(a.id) && this.getName().equals(a.name) && this.getDate().equals(a.date);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        result = 31 * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
}
