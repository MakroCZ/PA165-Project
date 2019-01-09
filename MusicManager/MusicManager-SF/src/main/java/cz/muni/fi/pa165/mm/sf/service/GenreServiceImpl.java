package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.GenreDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yehor Safonov; 487596
 */

@Service
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDao;

    @Inject
    private SongService songService;

    @Override
    public Genre create(Genre genre) {
        genreDao.create(genre);
        return genre;
    }

    @Override
    public void delete(Genre genre) {
        genreDao.delete(genre);
    }

    @Override
    public void update(Genre genre) {
        genreDao.update(genre);
    }

    @Override
    public List<Genre> findAll(){
        return genreDao.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> findByName(String name){
        return genreDao.findByName(name);
    }

    @Override
    public List<Song> getAllSongsWithSameGenre(Genre genre) {
        List<Song> allSongs = songService.findAll();
        List<Song> foundSongs = new ArrayList<>();
        for(Song song:allSongs){
            if(song.getGenre().equals(genre)) {
                foundSongs.add(song);
            }
        }
        return foundSongs;
    }
}
