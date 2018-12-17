package cz.muni.fi.pa165.mm.daolayer.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

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
    private LocalTime length;

    @NotNull
    @Column(nullable=false)
    private LocalDate date;

    @ManyToOne(optional = false)
    private Album album;

    @ManyToOne(optional = false)
    private Genre genre;

    public Song(String name, LocalTime length, LocalDate date){
        this.setName(name);
        this.setLength(length);
        this.setDate(date);
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

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
        this.length = length;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String toString(){
        return "ID: " +this.getId() + " Name: " + this.getName() + " Length: " + this.getLength() + " Publish Date: " + this.getDate().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (id != null ? !id.equals(song.id) : song.id != null) return false;
        if (!name.equals(song.name)) return false;
        if (!length.equals(song.length)) return false;
        return date.equals(song.date);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + length.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
