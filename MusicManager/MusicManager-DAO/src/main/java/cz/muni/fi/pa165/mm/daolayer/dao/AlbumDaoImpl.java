package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
@Repository
@Transactional
public class AlbumDaoImpl implements AlbumDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Album album) {
        em.persist(album);
    }

    @Override
    public Album findById(long id) {
        return em.find(Album.class, id);
    }

    @Override
    public List<Album> findAll() {
        return em.createQuery("select album from Album album", Album.class).getResultList();
    }

    @Override
    public void update(Album album) {
        em.merge(album);
    }

    @Override
    public void delete(Album album) {
        em.remove(em.merge(album));
    }
}
