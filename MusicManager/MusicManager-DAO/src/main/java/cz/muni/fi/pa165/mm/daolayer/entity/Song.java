package cz.muni.fi.pa165.mm.daolayer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Lukáš Suchánek; 433564
 */
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String name;

    @NotNull
    @Column(nullable=false)
    private LocalTime songLength;

    @NotNull
    @Column(nullable=false)
    private LocalDate releaseDate;

    @ManyToOne(optional = false)
    private Album album;

    @ManyToOne(optional = false)
    private Genre genre;

    public Song(String name, LocalTime length, LocalDate date){
        this.setName(name);
        this.setSongLength(length);
        this.setReleaseDate(date);
    }

    public Song(){}


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

    public LocalTime getSongLength() {
        return songLength;
    }

    public void setSongLength(LocalTime songLength) {
        this.songLength = songLength;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString(){
        return "ID: " +this.getId() + " Name: " + this.getName() + " Length: " + this.getSongLength() + " Publish Date: " + this.getReleaseDate().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this.getId() == null) {
            return false;
        }
        if (!(o instanceof Song)) {
            return false;
        }
        Song p = (Song) o;
        return this.getId().equals(p.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
