package cz.muni.fi.pa165.musicmanager.backend.dao;

import cz.muni.fi.pa165.musicmanager.backend.entity.Album;

import java.util.List;

public interface AlbumDao {
    /**
     * Creates instance of Album entity and saves it to database.
     * @param album represents album of songs from specific performer.
     */
    public void create(Album album);

    /**
     * Returns instance of Album entity for specified id.
     * @param id is uniquely identifying each instance of Album entity.
     * @throws IllegalArgumentException if the object is null.
     * @return the found Album instance or null if the entity does not exist
     */
    public Album retrieve(long id);

    /**
     * Returns all instances of Album entity.
     * @return the found Album instances or null if the entity does not exist
     */
    public List<Album> retrieveAll();

    /**
     * Overwrites data of Album instance with data from given instance.
     * IDs of both instances must match.
     * @param album represents album of songs from specific performer.
     */
    public void update(Album album);

    /**
     * Removes instance of Album entity from the database,
     * @param album represents album of songs from specific performer.
     * @throws IllegalArgumentException if the object is null.
     */
    public void delete(Album album);
}