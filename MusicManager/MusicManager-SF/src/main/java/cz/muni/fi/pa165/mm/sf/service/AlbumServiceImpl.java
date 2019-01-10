package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Inject
    private AlbumDao albumDao;
    @Inject
    private PerformerService performerService;

    @Override
    public Album create(Album album) {
        albumDao.create(album);
        return album;
    }

    @Override
    public Album find(long id) {
        return albumDao.findById(id);
    }

    @Override
    public List<Album> findAll() {
        return albumDao.findAll();
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void delete(Album album) {
        albumDao.delete(album);
    }

    @Override
    public List<Album> findAlbumsFromCountry(String country) {
        List<Performer> performers = performerService.findAll();
        List<Album> result = new ArrayList<>();
        for (Performer performer : performers) {
            if (performer.getCountry().equals(country)) {
                result.addAll(performer.getAlbums());
            }
        }
        return result;
    }
}
