package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yehor Safonov; 487596
 */

@Service
public interface GenreService {

    Genre create(Genre genre);

    void delete(Genre genre);

    void update(Genre genre);

    List<Genre> getAllGenres();

    Genre findById(Long id);

    List<Genre> findByName(String name);

    List<Song> getAllSongsWithSameGenre(Genre genre);
}
