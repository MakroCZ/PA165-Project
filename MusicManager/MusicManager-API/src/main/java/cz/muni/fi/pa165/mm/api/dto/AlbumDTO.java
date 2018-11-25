package cz.muni.fi.pa165.mm.api.dto;

import java.time.LocalDate;

/**
 * @author Václav Stehlík; 487580
 */
public class AlbumDTO {
    private Long id;

    private String name;

    private LocalDate date;

    private PerformerDTO performer;

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

    public PerformerDTO getPerformer() {
        return performer;
    }

    public void setPerformer(PerformerDTO performer) {
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
        if (!(o instanceof AlbumDTO)) {
            return false;
        }

        AlbumDTO a = (AlbumDTO) o;
        return this.getName().equals(a.getName()) && this.getDate().equals(a.getDate()) && this.getPerformer().equals(a.getPerformer());
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((performer == null) ? 0 : performer.hashCode());
        return result;
    }
}
