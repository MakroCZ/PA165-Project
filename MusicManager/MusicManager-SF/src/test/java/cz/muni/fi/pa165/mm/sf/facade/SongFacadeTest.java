package cz.muni.fi.pa165.mm.sf.facade;

import cz.muni.fi.pa165.mm.api.dto.AlbumDTO;
import cz.muni.fi.pa165.mm.api.dto.GenreDTO;
import cz.muni.fi.pa165.mm.api.dto.SongCreateDTO;
import cz.muni.fi.pa165.mm.api.dto.SongDTO;
import cz.muni.fi.pa165.mm.api.facade.SongFacade;
import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.sf.service.*;
import cz.muni.fi.pa165.mm.sf.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This java class represents unit tests for the SongFacate layer
 * @author Yehor Safonov; 487596
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SongFacadeTest  {

    @Mock
    private SongService songService;

    @Mock
    private AlbumService albumService;

    @Mock
    private GenreService genreService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private SongFacade songFacade;

    private SongDTO songDTO;
    private GenreDTO genreDTO;
    private Song song;
    private Album album;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeMethod(){

        /*
            Creating a genreDTODTO and setting its parametrs
        */

        genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Rock");
        genreDTO.setDescription("Rock music is a broad genreDTO of popular music that originated as" +
                " \"rock and roll\" in the United States in the early 1950s, and developed into" +
                " a range of different styles in the 1960s and later, particularly in the United" +
                " Kingdom and in the United States");

         /*
             Creating an albumDTO and setting its parametrs
         */

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(2L);
        albumDTO.setReleaseDate(LocalDate.of(2015, Month.APRIL, 16));
        albumDTO.setName("Final masquerade");


        album = new Album();
        album.setId(albumDTO.getId());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setName(albumDTO.getName());

        songDTO = new SongDTO();
        songDTO.setId(3L);
        songDTO.setName("In the end");
        songDTO.setAlbum(albumDTO);
        songDTO.setGenre(genreDTO);
        songDTO.setSongLength(LocalTime.of(0,4,45));
        songDTO.setReleaseDate(LocalDate.of(2014, Month.MAY, 10));
    }

    @AfterMethod
    void reset(){
        Mockito.reset(songService);
        Mockito.reset(genreService);
        Mockito.reset(albumService);
        Mockito.reset(beanMappingService);
    }

    @Test
    public  void  testCreateSong(){
        SongCreateDTO songCreateDTO = new SongCreateDTO();
        songCreateDTO.setName(songDTO.getName());
        songCreateDTO.setReleaseDate(songDTO.getReleaseDate());
        songCreateDTO.setSongLength(songDTO.getSongLength());
        songCreateDTO.setAlbumId(album.getId());
        songCreateDTO.setGenreId(genreDTO.getId());


        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());

        song = new Song();
        song.setId(songDTO.getId());
        song.setName(songDTO.getName());
        song.setReleaseDate(songDTO.getReleaseDate());
        song.setSongLength(songDTO.getSongLength());
        song.setGenre(genre);
        song.setAlbum(album);

        Mockito.doReturn(songCreateDTO).when(beanMappingService).mapTo(song, SongCreateDTO.class);
        Mockito.doReturn(song).when(beanMappingService).mapTo(songCreateDTO, Song.class);

        Mockito.doReturn(album).when(albumService).find(any(Long.class));
        Mockito.doReturn(genre).when(genreService).findById(any(Long.class));
        Mockito.doReturn(song).when(songService).create(any());

        Long id = songFacade.createSong(songCreateDTO);
        Mockito.verify(songService).create(song);

        Assert.assertEquals(id, song.getId());
    }

    @Test
    public void testUpdateSong() {
        Mockito.doReturn(song).when(beanMappingService).mapTo(songDTO, Song.class);
        doNothing().when(songService).update(any(Song.class));
        songFacade.updateSong(songDTO);
        verify(songService, times(1)).update(song);
    }

    @Test
    public  void  testDeleteSong(){
        song = new Song();
        song.setId(songDTO.getId());
        song.setName(songDTO.getName());
        song.setSongLength(songDTO.getSongLength());
        song.setReleaseDate(songDTO.getReleaseDate());

        Mockito.doNothing().when(songService).delete(any(Song.class));
        songFacade.deleteSong(songDTO.getId());
        verify(songService).delete(any(Song.class));
    }

    @Test
    public  void testFindAllSongs(){
        Song test_song = new Song();
        test_song.setName("Somewhere I belong");
        test_song.setSongLength(LocalTime.of(0,3,45));
        test_song.setReleaseDate(LocalDate.of(2015, Month.JANUARY, 12));

        List<Song> songs = new ArrayList<>();
        songs.add(test_song);

        List<SongDTO> songsDTO = new ArrayList<>();
        songsDTO.add(songDTO);

        Mockito.when( beanMappingService.mapTo(songs, SongDTO.class)).thenReturn(songsDTO);
        Mockito.when(songService.findAll()).thenReturn(songs);
        List<SongDTO> foundSongsDTO = songFacade.findAll();
        Mockito.verify(songService).findAll();
        Assert.assertEquals(foundSongsDTO.size(), songs.size());
    }

    @Test
    public void testGetSongWithID(){
       Song test_song = new Song();
       test_song.setName("Somewhere I belong");
       test_song.setSongLength(LocalTime.of(0,3,45));
       test_song.setReleaseDate(LocalDate.of(2015, Month.JANUARY, 12));

       Mockito.doReturn(test_song).when(songService).findById(4L);
       Mockito.doReturn(songDTO).when(beanMappingService).mapTo(test_song, SongDTO.class);
       Mockito.doReturn(songDTO).when(beanMappingService).mapTo(test_song, SongDTO.class);
       SongDTO songDTO1 = songFacade.findSongWithID(4L);
       Assert.assertEquals(songDTO, songDTO1);
    }
}
