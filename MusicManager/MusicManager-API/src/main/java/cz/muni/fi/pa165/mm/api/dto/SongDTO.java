package cz.muni.fi.pa165.mm.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Lukas Suchanek; 433654
 */
public class SongDTO {

    private String name;
    private LocalTime length;
    private LocalDate date;
    private AlbumDTO album;
    private GenreDTO genre;

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

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongDTO that = (SongDTO) o;

        if (!name.equals(that.name)) return false;
        if (!length.equals(that.length)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + length.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    public String toString(){
        return "SongDTO{" +
                "name: " + name + ", length: " + length.toString() + ", date: " + date.toString();
               // ", album: " + album + ", genre: " + genre +'}';
    }

}
