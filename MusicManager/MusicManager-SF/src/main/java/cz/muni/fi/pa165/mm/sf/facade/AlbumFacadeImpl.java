package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.AlbumCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.facade.AlbumFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.BeanMappingService;
import cz.muni.fi.pa165.mm.sf.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class AlbumFacadeImpl implements AlbumFacade {
    @Inject
    AlbumService albumService;

    @Inject
    PerformerService performerService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createAlbum(AlbumCreateDTO albumCreateDTO) {
        Album mappedAlbum = beanMappingService.mapTo(albumCreateDTO, Album.class);
        Performer performer = performerService.findById(albumCreateDTO.getPerformerId());
        mappedAlbum.setPerformer(performer);
        Album newAlbum = albumService.create(mappedAlbum);
        return newAlbum.getId();
    }

    @Override
    public void deleteAlbum(Long id) {
        Album album = new Album();
        album.setId(id);
        albumService.delete(album);
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        return beanMappingService.mapTo(albumService.retrieveAll(), AlbumDTO.class);
    }

    @Override
    public AlbumDTO getAlbumWithID(Long id) {
        return beanMappingService.mapTo(albumService.retrieve(id), AlbumDTO.class);
    }
}
