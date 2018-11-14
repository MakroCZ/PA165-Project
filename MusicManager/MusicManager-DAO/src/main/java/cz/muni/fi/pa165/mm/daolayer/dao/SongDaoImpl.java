package cz.muni.fi.pa165.mm.daolayer.dao;

import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Lukáš Suchánek; 433564
 */
@Repository
public class SongDaoImpl implements SongDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Song song) {
        em.persist(song);
    }

    @Override
    public void update(Song song) {
        em.merge(song);
    }

    @Override
    public void delete(Song song) {
        em.remove(em.merge(song));
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery("select s from Song s", Song.class).getResultList();
    }

    @Override
    public Song findById(Long id) {
       return  em.find(Song.class, id);
    }
}
