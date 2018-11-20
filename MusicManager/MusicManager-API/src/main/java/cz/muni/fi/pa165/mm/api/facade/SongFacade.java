package cz.muni.fi.pa165.mm.api.facade;

import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;

import java.util.List;

/**
 * Created by lsuchanek on 18.11.2018.
 */
public interface SongFacade {
    Long createSong(SongCreateDTO s);
    void deleteSong(Long id);
    List<SongDTO> getAllSongs();
    SongDTO getSongWithID(Long id);
}
