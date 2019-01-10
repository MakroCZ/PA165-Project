package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;

import java.util.List;

/**
 * @author Lukas Suchanek; 433654
 */
public interface SongFacade {

    /**
     * Add song to database
     * @param s Song to add
     * @return Id of created song
     */
    Long createSong(SongCreateDTO s);

    /**
     * Updates song.
     * @param s Song to update.
     */
    void updateSong(SongDTO s);

    /**
     * Remove song from database
     * @param id of song to be deleted
     */
    void deleteSong(Long id);

    /**
     * List all songs
     * @return list of all songs
     */
    List<SongDTO> findAll();

    /**
     * Find song with id
     * @param id of song to be found
     * @return song
     */
    SongDTO findSongWithID(Long id);

    List<SongDTO> findAllSongsFromSamePerformer(SongDTO s);
}
