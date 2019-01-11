package cz.muni.fi.pa165.mm.api.dto;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.pa165.mm.api.LocalDateDeserializer;
import cz.muni.fi.pa165.mm.api.LocalDateSerializer;
import cz.muni.fi.pa165.mm.api.LocalTimeDeserializer;
import cz.muni.fi.pa165.mm.api.LocalTimeSerializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author Lukas Suchanek; 433654
 */
public class SongCreateDTO {
    @NotNull
    @Size(min = 2, max=50)
    private String name;
    @NotNull
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime songLength;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @NotNull
    private Long albumId;
    @NotNull
    private Long genreId;


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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongCreateDTO that = (SongCreateDTO) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + ((songLength == null) ? 0 : songLength.hashCode());
        result = 31 * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        return result;
    }
}
