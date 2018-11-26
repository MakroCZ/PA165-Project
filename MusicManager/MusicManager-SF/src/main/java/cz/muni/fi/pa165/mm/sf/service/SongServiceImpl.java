package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.SongDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Service;
//import jav

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

/**
 * Created by lsuchanek on 18.11.2018.
 */
@Service
public class SongServiceImpl implements SongService{
    @Inject
    private SongDao songDao;

    @Override
    public Song create(Song song){
        songDao.create(song);
        return song;
    }
    @Override
    public void delete(Song song){

        if(song.getId()== null){
            throw new IllegalArgumentException("Song id can't be null");
        }
        songDao.delete(song);
    }
    @Override
    public void update(Song song){
        if(song.getId()== null){
            throw new IllegalArgumentException("Song id can't be null");
        }
        songDao.update(song);
    }
    @Override
    public List<Song> findAll(){
        return songDao.findAll();
    }

    @Override
    public Song findById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id is null");
        }
        return songDao.findById(id);
    }

    @Override
    public List<Song> findAllSongsFromSamePerformer(Song song){
        Set<Album> albums = song.getAlbum().getPerformer().getAlbums();
        List<Song> songs = new ArrayList<>();
        for(Album album:albums){
            songs.addAll(album.getSongs());
        }
        return songs;
    }
}
