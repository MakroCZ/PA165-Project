package cz.muni.fi.pa165.musicmanager.backend.dao;

import cz.muni.fi.pa165.musicmanager.backend.entity.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AlbumDaoImpl implements AlbumDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Album album) {
        em.persist(album);
    }

    @Override
    public Album retrieve(long id) {
        return  em.find(Album.class, id);
    }

    @Override
    public List<Album> retrieveAll() {
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
