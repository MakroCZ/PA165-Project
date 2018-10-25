package cz.muni.fi.pa165.musicmanager.backend.dao;

import cz.muni.fi.pa165.musicmanager.backend.entity.Song;

import java.util.List;

/**
 * @author Lukáš Suchánek; 433564
 */
public interface SongDao {
    /**
     *  adds song
     *  throws IllegalArgumentException if something is null or even song is null
     * @param song
     */
    void create(Song song);

    /**
     * change song with same ID to this new
     * throws IllegalArgumentException if something is null or even song to update is null
     * @param song
     */
    void update(Song song);

    /**
     * deletes song
     * throws IllegalArgumentException if song is null or does not exist
     * @param song
     */
    void delete(Song song);

    /**
     *
     * @return list of all songs
     */
    List<Song> findAll();

    /**
     * method to return song
     * throws IllegalArgumentException if id does not exist
     * @param id of song
     * @return song with id
     */
    Song findById(Long id);
}
