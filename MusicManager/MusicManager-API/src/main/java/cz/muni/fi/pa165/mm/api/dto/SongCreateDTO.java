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
    private LocalTime length;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
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
        result = 31 * result + ((length == null) ? 0 : length.hashCode());
        result = 31 * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
}
