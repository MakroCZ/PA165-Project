package cz.muni.fi.pa165.mm.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.muni.fi.pa165.mm.api.LocalDateDeserializer;
import cz.muni.fi.pa165.mm.api.LocalDateSerializer;

import java.time.LocalDate;

/**
 *
 * @author Marek Barinka; 456295
 */
public class PerformerCreateDTO {

    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (this.getId() == null) {
            return false;
        }
        if (!(other instanceof PerformerCreateDTO)) {
            return false;
        }
        PerformerCreateDTO p = (PerformerCreateDTO) other;
        return this.getId().equals(p.getId());
    }
    
    @Override
    public int hashCode() {
        int result = 31;
        if (this.getId() == null) {
            return result;
        }
        return result * this.getId().hashCode();
    }*/
}
