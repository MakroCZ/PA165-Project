package cz.muni.fi.pa165.mm.api.dto;

/**
 * @author Yehor Safonov; 487596
 */
public class GenreDTO {

    private Long id;

    private String name;

    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public String toString(){
        return "GenreDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GenreDTO))
            return false;
        GenreDTO genre = (GenreDTO) obj;
        if  (!id.equals(genre.getId()))
            return false;
        if (!name.equals(genre.getName()))
            return false;
        if (!description.equals(genre.getDescription()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }
}
