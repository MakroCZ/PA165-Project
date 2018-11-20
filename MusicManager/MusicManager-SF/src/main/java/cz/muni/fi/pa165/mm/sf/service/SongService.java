package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lsuchanek on 18.11.2018.
 */
@Service
public interface SongService {
    Song create(Song song);

    void delete(Song song);

    void update(Song song);
    List<Song> findAll();

    Song findById(Long id);

    List<Song> findAllSongsFromSamePerformer(Song song);
}
