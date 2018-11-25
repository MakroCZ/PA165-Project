package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lukas Suchanek; 433654
 */
@Service
public interface SongService {
    /**
     * Add Song to database
     * @param song
     * @return created song
     */
    Song create(Song song);

    /**
     * Remove song from database
     * @param song
     */
    void delete(Song song);

    /**
     * Update song
     * @param song
     */
    void update(Song song);

    /**
     * List all songs in database
     * @return list of retrieved songs
     */
    List<Song> findAll();

    /**
     * find song by id
     * @param id of song to find
     * @return retrieved song
     */
    Song findById(Long id);

    /**
     * List all songs from performer of particular song
     * @param song song from which performer we want to find his songs
     * @return list of retrieved songs
     */
    List<Song> findAllSongsFromSamePerformer(Song song);
}
