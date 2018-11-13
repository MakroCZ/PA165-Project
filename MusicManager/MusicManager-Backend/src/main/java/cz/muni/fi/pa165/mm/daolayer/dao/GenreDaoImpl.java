package cz.muni.fi.pa165.musicmanager.backend.dao;

import cz.muni.fi.pa165.musicmanager.backend.entity.Genre;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * GenreDoaImpl is a java class which implements Genre DAO interface
 * @author Yehor Safonov; 487596
 */

@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Genre genre) throws IllegalArgumentException {
        em.persist(genre);
    }

    @Override
    public void update(Genre genre) throws IllegalArgumentException {
        em.merge(genre);
    }

    @Override
    public void delete(Genre genre) throws IllegalArgumentException {
        em.remove(em.merge(genre));
    }

    @Override
    public Genre findById(Long id) throws IllegalArgumentException {
        return em.find(Genre.class,id);
    }

    @Override
    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name",
                Genre.class).setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }
}
