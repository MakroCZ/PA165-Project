package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a GenereService leyer.
 * GenreService interface defines common CRUD operations and
 * works with entities using GenreDTO. The interface defines follow methods: the {@link #create(Genre)} method,
 * the {@link #update(Genre)} method, the {@link #delete(Genre)} method
 * and methods returning specific genre, there are {@link #findByName(String)},
 * {@link #findById(Long)} and {@link #getAllGenres()} methods.
 * One business function is implemented {@link #getAllSongsWithSameGenre(Genre)}.
 * @author Yehor Safonov; 487596
 */
@Service
public interface GenreService {

    /**
     * This method is used for creating a new genre using GenreDTO.
     * @throws IllegalArgumentException in the case of an illegal or inappropriate genre object.
     * The exception may appear in the case of null object or if some @NotNull parameters have null argument
     * @param genre
     */
    Genre create(Genre genre);

    /**
     * This method is used for deleting existing Genre using GenreDTO
     * @throws IllegalArgumentException in the case of an illegal or inappropriate argument
     * The exception may appear in the case of null object or if some @NotNull parameters have null argument
     * @param genre
     */
    void delete(Genre genre);

    /**
     * This method is used for updating the Ganre with the same id to the new one using a GenreDTO entity
     * @throws IllegalArgumentException in the case of an illegal or inappropriate genre object.
     * The exception may appear in the case of null object or if some @NotNull parameters have null argument
     * @param genre
     */
    void update(Genre genre);

    /**
     * This method returns the list of all genres which are presented in the database using a GenreDTO entity.
     * In case there are no Genre items in the database, the method returns empty list.
     * @return list of genres
     */
    List<Genre> getAllGenres();

    /**
     * This method returns the Genre with required id
     * @param id of genre is Long
     * @return genre with specific id
     */
    Genre findById(Long id);

    /**
     * This method returns list of Genres witch is equal to the specific name using a GenreDTO entity.
     * returns empty list of Genres if there is no object presented in the database
     * @param name
     * @return list of genres
     */
    List<Genre> findByName(String name);

    /**
     * This method returns list of Songs witch have the same genre. This business function uses
     * GenreDTO and SongDTO entities.
     * returns empty list of Songs if there is no object presented in the database
     * @param genre
     * @return list of songs
     */
    List<Song> getAllSongsWithSameGenre(Genre genre);
}
