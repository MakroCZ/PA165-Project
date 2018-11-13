package cz.muni.fi.pa165.musicmanager.backend.dao;
import cz.muni.fi.pa165.musicmanager.backend.entity.Genre;
import java.util.List;

/**
 * This interface represents Genre Data Access Object (DAO).
 * GenreDao interface defines common CRUD operations, e.g.
 * the {@link #create(Genre)} method, the {@link #update(Genre)} method,
 * the {@link #delete(Genre)} method and methods returning specific genre,
 * there are {@link #findByName(String)}, {@link #findById(Long)} and
 * {@link #getAllGenres()} methods.
 *
 * @author Yehor Safonov; 487596
 */
public interface GenreDao {

    /**
     * This method is used for creating a new genre
     * @throws IllegalArgumentException in the case of an illegal or inappropriate argument
     * @param genre
     */
    void create(Genre genre) throws IllegalArgumentException;

    /**
     * This method is used for updating the Ganre with the same id to the new one
     * @throws IllegalArgumentException in the case of an illegal or inappropriate argument
     * @param genre
     */
    void update(Genre genre) throws IllegalArgumentException;

    /**
     * This method is used for deleting existing Genre
     * @throws IllegalArgumentException in the case of an illegal or inappropriate argument
     * @param genre
     */
    void delete(Genre genre) throws IllegalArgumentException;

    /**
     * This method returns the Genre with required id
     * @throws IllegalArgumentException in the case of an illegal argument
     * @param id of genre
     * @return genre with specific id
     */
    Genre findById(Long id) throws IllegalArgumentException;

    /**
     * This method returns list of Genres witch is equal to the specific name
     * returns empty list of Genres if there is no object presented in the database
     * @param name
     * @return list of genres
     */
    List<Genre> findByName(String name);

    /**
     * This method returns the list of all genres which are present in the database.
     * In case there are no Genre items in the database, the method returns empty list.
     * @return list of genres
     */
    List<Genre> getAllGenres();
}
