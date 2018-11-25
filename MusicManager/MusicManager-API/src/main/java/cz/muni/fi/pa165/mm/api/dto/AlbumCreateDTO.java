package cz.muni.fi.pa165.mm.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Václav Stehlík; 487580
 */
public class AlbumCreateDTO {
    @NotNull
    @Size(min = 2, max=50)
    private String name;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long performerId;

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

    public Long getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Long performerId) {
        this.performerId = performerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof AlbumCreateDTO)) {
            return false;
        }

        AlbumCreateDTO a = (AlbumCreateDTO) o;
        return this.getName().equals(a.getName()) && this.getDate().equals(a.getDate()) && this.getPerformerId().equals(a.getPerformerId());
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((performerId == null) ? 0 : performerId.hashCode());
        return result;
    }
}
