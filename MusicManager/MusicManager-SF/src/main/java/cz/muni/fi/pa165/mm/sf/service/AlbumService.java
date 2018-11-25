package cz.muni.fi.pa165.mm.sf.service;

import cz.muni.fi.pa165.mm.daolayer.entity.Album;

import java.util.List;

/**
 * @author Václav Stehlík; 487580
 */
public interface AlbumService {
    Album create(Album album);

    Album retrieve(long id);

    List<Album> retrieveAll();

    void update(Album album);

    void delete(Album album);
}
