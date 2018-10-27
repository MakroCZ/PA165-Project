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

    @OneToMany(mappedBy = "albums")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(getId(), album.getId()) &&
                Objects.equals(getName(), album.getName()) &&
                Objects.equals(getDate(), album.getDate()) &&
                Objects.equals(getSongs(), album.getSongs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDate(), getSongs());
    }
}
