package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Album;

import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
public interface AlbumService {
    /**
     * Adds album to the database.
     * @param album Album to add.
     * @return created album.
     */
    Album create(Album album);

    /**
     * Finds album with specified id.
     * @param id Identifier of the album to find.
     * @return Album with specified id or null if the entity does not exist.
     */
    Album retrieve(long id);

    /**
     * Finds all albums in the database.
     * @return List containing all albums from the database or null if no album is stored.
     */
    List<Album> retrieveAll();

    /**
     * Updates album.
     * @param album Album to update.
     */
    void update(Album album);

    /**
     * Deletes album from the database.
     * @param album Identifier of the album to be deleted.
     */
    void delete(Album album);

    /**
     * Finds albums produced by performers from specified country.
     * @param country Performer's country.
     * @return List containing all albums from the database or null if no album is stored.
     */
    List<Album> retrieveAlbumsFromCountry(String country);
}
