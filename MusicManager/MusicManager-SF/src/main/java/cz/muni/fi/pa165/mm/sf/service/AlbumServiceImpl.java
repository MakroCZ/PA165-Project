package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.AlbumDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDao albumDao;

    @Autowired
    public AlbumServiceImpl(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    @Override
    public Album create(Album album) {
        albumDao.create(album);
        return album;
    }

    @Override
    public Album retrieve(long id) {
        return albumDao.retrieve(id);
    }

    @Override
    public List<Album> retrieveAll() {
        return albumDao.retrieveAll();
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void delete(Album album) {
        albumDao.delete(album);
    }
}
