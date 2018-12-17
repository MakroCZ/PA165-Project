package cz.muni.fi.pa165.mm.api.dto;



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
    private LocalTime length;
    @NotNull
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

        if (!name.equals(that.name)) return false;
        if (!length.equals(that.length)) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + ((length == null) ? 0 : length.hashCode());
        result = 31 * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
}
