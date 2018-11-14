package cz.muni.fi.pa165.musicmanager.backend.service;

import cz.muni.fi.pa165.mm.daolayer.dao.SongDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by lsuchanek on 23.10.2018.
 */
@Service
@Transactional
public class SongServiceImpl{
    @Autowired
    private SongDao songDao;


    public void create(Song song){
        validateSong(song);
        songDao.create(song);
    }

    public void delete(Song song){

        validateSong(song);
        if(song.getId()== null){
            throw new IllegalArgumentException("Song id can't be null");
        }
        songDao.delete(song);
    }

    public void update(Song song){
        validateSong(song);
        if(song.getId()== null){
            throw new IllegalArgumentException("Song id can't be null");
        }
        songDao.update(song);
    }

    public List<Song> findAll(){
        return songDao.findAll();
    }


    public Song findById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id is null");
        }
        return songDao.findById(id);
    }

    private static void validateSong(Song song){
        if(song == null){
            throw new IllegalArgumentException("Song is null");
        }
        if (song.getName() == null){
            throw new IllegalArgumentException("name is null");
        }
        if(song.getLength() == null){
            throw new IllegalArgumentException("length is null");
        }
        if(song.getDate() == null){
            throw new IllegalArgumentException("date is null");
        }
        if(song.getDate().isAfter(LocalDate.now()) ){
            throw new IllegalArgumentException("Date can't be in the future");
        }
    }
}
