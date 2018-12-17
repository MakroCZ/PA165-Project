package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.mm.daolayer.entity.Album;
import cz.muni.fi.pa165.mm.daolayer.entity.Genre;
import cz.muni.fi.pa165.mm.daolayer.entity.Performer;
import cz.muni.fi.pa165.mm.daolayer.entity.Song;
import cz.muni.fi.pa165.mm.daolayer.entity.User;
import cz.muni.fi.pa165.mm.sf.service.AlbumService;
import cz.muni.fi.pa165.mm.sf.service.GenreService;
import cz.muni.fi.pa165.mm.sf.service.PerformerService;
import cz.muni.fi.pa165.mm.sf.service.SongService;
import cz.muni.fi.pa165.mm.sf.service.UserService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Marek
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private PerformerService performerService;
    
    @Autowired
    private AlbumService albumService;
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private UserService userService;
    
    @Override
    public void loadData() {
        Genre rock = genre("Rock", "Rock music is a broad genre of popular music that originated as \"rock and roll\" in the United States in the early 1950s, and developed into a range of different styles in the 1960s and later, particularly in the United Kingdom and in the United States.");
        Genre hardRock = genre("Hard rock", "Hard rock is a loosely defined subgenre of rock music that began in the mid-1960s. It is typified by a heavy use of aggressive vocals, distorted electric guitars, bass guitar, drums, and often accompanied with keyboards.");
        
        
        Performer queen = performer("Queen", LocalDate.of(1971, Month.JANUARY, 1), "England");
        
        Album aNightAtTheOpera = album("A Night at the Opera", LocalDate.of(1975, Month.NOVEMBER, 21), queen);
        Song deathOnTwoLegs = song("Death on Two Legs", LocalTime.of(0, 3, 43), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        Song lazingOnASundayAfternoon = song("Lazing on a Sunday Afternoon", LocalTime.of(0, 1, 7), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, hardRock);
        Song imInLoveWithMyCar = song("I'm in Love with My Car", LocalTime.of(0, 3, 5), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, hardRock);
        Song youreMyBestFriend = song("You're My Best Friend", LocalTime.of(0, 2, 52), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        Song song39 = song("'39", LocalTime.of(0, 3, 31), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        Song sweetLady = song("Sweet Lady", LocalTime.of(0, 4, 3), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        Song seasideRendezvous = song("Seaside Rendezvous", LocalTime.of(0, 2, 15), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        Song theProphetsSong = song("The Prophet's Song", LocalTime.of(0, 8, 21), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, hardRock);
        Song loveOfMyLife = song("Love of my Life", LocalTime.of(0, 3, 39), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, hardRock);
        Song goodCompany = song("Good Company", LocalTime.of(0, 3, 23), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, hardRock);
        Song bohemianRhapsody = song("Bohemian Rhapsody", LocalTime.of(0, 5, 55), LocalDate.of(1975, Month.OCTOBER, 31), aNightAtTheOpera, rock);
        Song godSaveTheQueen = song("God Save the Queen", LocalTime.of(0, 1, 18), LocalDate.of(1975, Month.NOVEMBER, 21), aNightAtTheOpera, rock);
        
        Album newsOfTheWorld = album("News of the World", LocalDate.of(1977, Month.OCTOBER, 28), queen);
        Song weWillRockYou = song("We Will Rock You", LocalTime.of(0, 2, 1), LocalDate.of(1977, Month.JANUARY, 28), newsOfTheWorld, hardRock);
        Song weAreTheChampions = song("We Are the Champions", LocalTime.of(0, 2, 59), LocalDate.of(1977, Month.OCTOBER, 7), newsOfTheWorld, hardRock);
        Song sheerHeartAttack = song("Sheer Heart Attack", LocalTime.of(0, 3, 24), LocalDate.of(1977, Month.JUNE, 15), newsOfTheWorld, rock);
        Song allDeadAllDead = song("All Dead, All Dead", LocalTime.of(0, 3, 9), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, hardRock);
        Song spreadYourWings = song("Spread Your Wings", LocalTime.of(0, 4, 32), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, rock);
        Song fightFromTheInside = song("Fight From The Inside", LocalTime.of(0, 3, 3), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, rock);
        Song getDownMakeLove = song("Get Down, Make Love", LocalTime.of(0, 3, 51), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, hardRock);
        Song sleepingOnTheSidewalk = song("Sleeping On The Sidewalk", LocalTime.of(0, 3, 7), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, rock);
        Song whoNeedsYou = song("Who Needs You", LocalTime.of(0, 3, 7), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, rock);
        Song itsLate = song("It's Late", LocalTime.of(0, 6, 27), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, rock);
        Song myMelancholyBlues = song("My Melancholy Blues", LocalTime.of(0, 3, 29), LocalDate.of(1977, Month.OCTOBER, 28), newsOfTheWorld, hardRock);
        
        
        Performer acdc = performer("AC/DC", LocalDate.of(1937, Month.JANUARY, 1), "Australia");
        
        Album highwayToHell = album("Highway to Hell", LocalDate.of(1979, Month.MARCH, 27), acdc);
        Song songHighwayToHell = song("Highway to Hell", LocalTime.of(0, 3, 29), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song girlsGotRhythm = song("Girls Got Rhythm", LocalTime.of(0, 3, 24), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song walkAllOverYou = song("Walk All Over You", LocalTime.of(0, 5, 10), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song touchTooMuch = song("Touch Too Much", LocalTime.of(0, 4, 28), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song beatingAroundTheBush = song("Beating Around the Bush", LocalTime.of(0, 3, 57), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song shotDownInFlames = song("Shot Down in Flames", LocalTime.of(0, 3, 23), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song getItHot = song("Get It Hot", LocalTime.of(0, 2, 35), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song ifYouWantBlood = song("If You Want Blood (You've Got It)", LocalTime.of(0, 4, 38), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song loveHungryMan = song("Love Hungry Man", LocalTime.of(0, 4, 18), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        Song nightProwler = song("Night Prowler", LocalTime.of(0, 6, 18), LocalDate.of(1979, Month.MARCH, 27), highwayToHell, hardRock);
        
        
        
        createUser("admin", "admin");
        createUser("Pepa", "1234");
        createUser("Franta", "ABcd");
    }
    
    private void createUser(String username, String passwd) {
        User u = new User();
        u.setUsername(username);
        userService.registerUser(u, passwd);
    }
    
    private Performer performer(String name, LocalDate date, String country) {
        Performer p = new Performer();
        p.setName(name);
        p.setStartDate(date);
        p.setCountry(country);
        performerService.create(p);
        return p;
    }
    
    private Album album(String name, LocalDate date, Performer p) {
        Album a = new Album();
        a.setName(name);
        a.setDate(date);
        a.setPerformer(p);
        albumService.create(a);
        return a;
    }
    
    private Song song(String name, LocalTime lenght, LocalDate date, Album a, Genre g) {
        Song s = new Song();
        s.setName(name);
        s.setLength(lenght);
        s.setDate(date);
        s.setAlbum(a);
        s.setGenre(g);
        songService.create(s);
        return s;
    }
    
    private Genre genre(String name, String desc) {
        Genre g = new Genre();
        g.setName(name);
        g.setDescription(desc);
        genreService.create(g);
        return g;
    }
}