package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lukas Suchanek; 433654
 */
@Service
@Transactional
public class SongFacadeImpl implements SongFacade {
    final static Logger log = LoggerFactory.getLogger(SongFacadeImpl.class);
    @Autowired
    SongService songService;

    @Autowired
    GenreService genreService;

    @Autowired
    AlbumService albumService;

    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public Long createSong(SongCreateDTO s) {
        Song mappedSong = beanMappingService.mapTo(s, Song.class);
        Album album = albumService.find(s.getAlbumId());
        Genre genre = genreService.findById(s.getGenreId());
        mappedSong.setAlbum(album);
        mappedSong.setGenre(genre);
        Song newSong = songService.create(mappedSong);
        return newSong.getId();
    }

    @Override
    public void updateSong(SongDTO song) {
        Song mappedSong = beanMappingService.mapTo(song, Song.class);
        songService.update(mappedSong);
    }

    @Override
    public void deleteSong(Long id) {
        Song song = new Song();
        song.setId(id);
        songService.delete(song);
    }

    @Override
    public List<SongDTO> findAll() {
       return beanMappingService.mapTo(songService.findAll(), SongDTO.class);
    }

    @Override
    public SongDTO findSongWithID(Long id) {
       return beanMappingService.mapTo(songService.findById(id), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsFromSamePerformer(SongDTO s) {
        Song song = beanMappingService.mapTo(s, Song.class);
        return beanMappingService.mapTo(songService.findAllSongsFromSamePerformer(song), SongDTO.class);
    }
}
