package cz.muni.fi.pa165.musicmanager.backend.service;

import cz.muni.fi.pa165.musicmanager.backend.dao.SongDao;
import cz.muni.fi.pa165.musicmanager.backend.entity.Song;
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
public class songServiceImpl {
    @Autowired
    private SongDao songDao;

    public void create(Song song){
        songDao.create(song);
    }

    public void delete(Song song){
        songDao.delete(song);
    }

    public void update(Song song){
        songDao.update(song);
    }

    public List<Song> findAll(){
        return songDao.findAll();
    }

    public Song findById(Long id){
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
        if(song.getLength()<=0){
            throw new IllegalArgumentException("length can't be 0 or less");
        }
        if(song.getDate() == null){
            throw new IllegalArgumentException("date is null");
        }
        if(song.getDate().isAfter(LocalDate.now()) ){
            throw new IllegalArgumentException("Date can't be in the future");
        }
    }
}
