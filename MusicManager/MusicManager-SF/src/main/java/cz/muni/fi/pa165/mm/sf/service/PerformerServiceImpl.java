package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.dao.PerformerDao;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marek Barinka; 456295
 */
@Service
public class PerformerServiceImpl implements PerformerService {

    @Inject
    private PerformerDao performerDao;

    @Override
    public void create(Performer p) {
        performerDao.create(p);
    }

    @Override
    public List<Performer> findAll() {
        return performerDao.findAll();
    }

    @Override
    public Performer findById(Long id) {
        return performerDao.findById(id);
    }

    @Override
    public void update(Performer p) {
        performerDao.update(p);
    }

    @Override
    public void remove(Performer p) {
        performerDao.remove(p);
    }

    @Override
    public void addAlbum(Performer p, Album a) {
        p.addAlbum(a);
    }
}
