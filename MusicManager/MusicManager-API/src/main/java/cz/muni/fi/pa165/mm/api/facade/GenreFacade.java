package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.GenreCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;

import java.util.List;

/**
 * This class represents a GenereFacade leyer.
 * GenreFacade interface operates with following methods and
 * directly works with GenreDTO objects using genreService layer. The interface defines follow methods:
 * the {@link #createGenre(GenreCreateDTO)} method, the {@link #deleteGenre(Long)} method
 * and methods returning specific genre, there are {@link #findWithId(Long)}, {@link #findAll()},
 * {@link #findWithName(String)} methods.
 * @author Yehor Safonov; 487596
 */
public interface GenreFacade {


    /**
     * This method allows to create a new genre using GenreService layer.
     * An argument of this method is GenreCreateDTO object.
     * @param genre
     * @return id of new Genre
     */
    Long createGenre(GenreCreateDTO genre);

    /**
     * Updates genre.
     * @param genre Genre to update.
     */
    void updateGenre(GenreDTO genre);

    /*
     * This method allows to delete an existing genre using its id identifier.
     * @param id of existing Genre
     */
    void deleteGenre(Long id);

    /*
     * This method returns the list of all genres which are presented.
     * @return list of existing genres
     */
    List<GenreDTO> findAll();

    /*
     * This method returns the GenreDTO object with required id
     * @return genreDTO object
     */
    GenreDTO findWithId(Long id);

    /**
     * This method returns list of GenreDTO objects witch have required name.
     * returns empty list of GenreDTO objects if there is no object presented in the database.
     * @param name
     * @return list of genreDTO objects
     */
    List<GenreDTO> findWithName(String name);
    
    /**
     * This method returns list of Songs witch have the same genre. This business function uses
     * GenreDTO and SongDTO entities.
     * returns empty list of Songs if there is no object presented in the database
     * @param id id of genre
     * @return list of songs
     */
    List<SongDTO> findAllSongsWithSameGenre(Long id);
}
