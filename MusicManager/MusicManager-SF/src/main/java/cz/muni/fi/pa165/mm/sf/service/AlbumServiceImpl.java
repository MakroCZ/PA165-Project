package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDao albumDao;
    private final PerformerService performerService;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao, PerformerService performerService) {
        this.albumDao = albumDao;
        this.performerService = performerService;
    }

    @Override
    public Album create(Album album) {
        validateAlbumEntity(album);
        albumDao.create(album);
        return album;
    }

    @Override
    public Album retrieve(long id) {
        validateId(id);
        return albumDao.retrieve(id);
    }

    @Override
    public List<Album> retrieveAll() {
        return albumDao.retrieveAll();
    }

    @Override
    public void update(Album album) {
        validateId(album.getId());
        validateAlbumEntity(album);
        albumDao.update(album);
    }

    @Override
    public void delete(Album album) {
        validateId(album.getId());
        validateAlbumEntity(album);
        albumDao.delete(album);
    }

    @Override
    public List<Album> retrieveAlbumsFromCountry(String country) {
        List<Performer> performers = performerService.findAll();
        List<Album> result = new ArrayList<>();
        for (Performer performer : performers) {
            if (performer.getCountry().equals(country)) {
                result.addAll(performer.getAlbums());
            }
        }
        return result;
    }

    private static void validateAlbumEntity(Album album) {
        if (album == null) throw new IllegalArgumentException("Album is null.");
        if (album.getName() == null) throw new IllegalArgumentException("Album name is null.");
        if (album.getDate() == null) throw new IllegalArgumentException("Album date is null.");
    }

    private static void validateId(Long id) {
        if (id == null) throw new IllegalArgumentException("Id is null.");
    }
}
