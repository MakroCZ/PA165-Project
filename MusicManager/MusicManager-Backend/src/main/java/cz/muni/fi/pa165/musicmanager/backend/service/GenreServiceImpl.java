package cz.muni.fi.pa165.musicmanager.backend.service;

import cz.muni.fi.pa165.musicmanager.backend.dao.GenreDao;
import cz.muni.fi.pa165.musicmanager.backend.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Yehor Safonov; 487596
 */

@Service
@Transactional
public class GenreServiceImpl {
    @Autowired
    private GenreDao genreDao;

    public void create(Genre genre){
        isValid(genre);
        genreDao.create(genre);
    }

    public void delete(Genre genre){
        isValid(genre);
        genreDao.delete(genre);
    }

    public void update(Genre genre){
        isValid(genre);
        genreDao.update(genre);
    }

    public List<Genre> getAllGenres(){
        return genreDao.getAllGenres();
    }

    public Genre findById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id is null");
        }
        return genreDao.findById(id);
    }

    public List<Genre> findByName(String name){
        return genreDao.findByName(name);
    }


    private static void isValid(Genre genre){
        if (genre.getId()== null) throw new IllegalArgumentException("Genre id is null");
        if (genre == null) throw new IllegalArgumentException("Genre is null");
        if (genre.getName() == null) throw new IllegalArgumentException("Genre name is null");
        if (genre.getDescription() == null) throw new IllegalArgumentException("Genre description is null");
    }
}
