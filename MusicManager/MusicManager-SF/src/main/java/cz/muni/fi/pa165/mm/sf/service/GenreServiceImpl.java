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
        validateGenre(genre);
        genreDao.create(genre);
        return genre;
    }

    @Override
    public void delete(Genre genre) {
        validateGenre(genre);
        validateId(genre.getId());
        genreDao.delete(genre);
    }

    @Override
    public void update(Genre genre) {
        validateGenre(genre);
        validateId(genre.getId());
        genreDao.update(genre);
    }

    @Override
    public List<Genre> getAllGenres(){
        return genreDao.getAllGenres();
    }

    @Override
    public Genre findById(Long id) {
        validateId(id);
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> findByName(String name){
        return genreDao.findByName(name);
    }

    @Override
    public List<Song> getAllSongsWithSameGenre(Genre genre) {
        validateGenre(genre);
        validateId(genre.getId());
        List<Song> allSongs = songService.findAll();
        List<Song> foundSongs = new ArrayList<>();
        for(Song song:allSongs){
            if(song.getGenre().equals(genre)) {
                foundSongs.add(song);
            }
        }
        return foundSongs;
    }

    private static void validateGenre(Genre genre){
        if (genre == null) throw new IllegalArgumentException("Genre is null");
        if (genre.getName() == null) throw new IllegalArgumentException("Genre name is null");
        if (genre.getDescription() == null) throw new IllegalArgumentException("Genre description is null");
    }
    
    private static void validateId(Long id){
        if (id == null) throw new IllegalArgumentException("Id is null");
    }
}
