package test.service;

import com.xeasony.Config;
import com.xeasony.model.Author;
import com.xeasony.model.Magazine;
import com.xeasony.model.Publication;
import com.xeasony.repository.AuthorRepository;
import com.xeasony.repository.MagazineRepository;
import com.xeasony.service.AuthorService;
import com.xeasony.service.MagazineParse;
import com.xeasony.service.MagazineService;
import com.xeasony.service.PublicationService;
import com.xeasony.util.Constant;
import org.hamcrest.collection.IsIterableContainingInOrder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class MagazineServiceTest {
    PublicationService service;
    List<Author> authors = new ArrayList<>();
    Magazine magazine1 = new Magazine("N", "1", authors, new Date());
    Magazine magazine2 = new Magazine("D", "2", authors, new Date());
    Magazine magazine3 = new Magazine("A", "3", authors, new Date());
    Magazine magazine33 = new Magazine("M", "3", authors, new Date());
    Magazine magazine4 = new Magazine("B", "4", authors, new Date());

    @org.junit.Before
    public void initialize() {
        service = new MagazineService(new MagazineRepository(), new AuthorService(new AuthorRepository()));
        authors = new ArrayList<>();
    }

    public void initAdd() {
        service.add(magazine1);
        service.add(magazine2);
    }

    @org.junit.Test
    public void testFind() throws Exception {
        initAdd();
        Magazine findMagazine = new Magazine("", "2", authors, new Date());
        assertSame(magazine2, service.find(findMagazine));

        findMagazine = new Magazine("", "22", authors, new Date());
        assertEquals(null, service.find(findMagazine));
    }

    @org.junit.Test
    public void testFindAll() throws Exception {
        initAdd();

        List<Magazine> magazines = new ArrayList<>();
        magazines.add(magazine1);
        magazines.add(magazine2);

        assertThat(service.findAll(), IsIterableContainingInOrder.contains(magazines.toArray()));
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        initAdd();

        List<Publication> magazines = new ArrayList<>(service.findAll());

        magazines.add(magazine3);
        service.add(magazine3);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(magazines.toArray()));

        service.add(magazine33);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(magazines.toArray()));

        magazines.add(magazine4);
        service.add(magazine4);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(magazines.toArray()));
    }

    @org.junit.Test
    public void testReadFromCsv() throws Exception {
        DateFormat format = new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT, Locale.GERMAN);
        Date releaseDate = format.parse("12.12.2002");
        Author author = new Author("", "", "pr-walter@optivo.de");
        authors.add(author);

        Magazine magazine = new Magazine("Der Weinkenner", "2547-8548-2541", authors, releaseDate);
        service.readFromCsvFile(Config.magazineFilePath, Constant.EURO_CHARSET, new MagazineParse());
        assertEquals(6, service.findAll().size());

        Magazine existed = (Magazine) service.find(magazine);

        assertEquals(magazine.getTitle(), existed.getTitle());
        assertEquals(magazine.getIsbn(), existed.getIsbn());
        assertEquals(magazine.getReleaseDate(), existed.getReleaseDate());
        assertEquals(magazine.getAuthors(), existed.getAuthors());
    }

    @org.junit.Test
    public void testFindByISBNOneResult() throws Exception {
        DateFormat format = new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT, Locale.GERMAN);
        Date releaseDate = format.parse("23.02.2004");
        Author author = new Author("", "", "pr-gustafsson@optivo.de");
        authors.add(author);

        Magazine magazine = new Magazine("Vinum", "1313-4545-8875", authors, releaseDate);

        service.readFromCsvFile(Config.magazineFilePath, Constant.EURO_CHARSET, new MagazineParse());

        List<Publication> foundMagazines = service.findByISBN(magazine.getIsbn());

        assertEquals(1, foundMagazines.size());

        Magazine firstMagazine = (Magazine) (service.findByISBN(magazine.getIsbn()).get(0));

        assertEquals(magazine.getTitle(), firstMagazine.getTitle());
        assertEquals(magazine.getIsbn(), firstMagazine.getIsbn());
        assertEquals(magazine.getReleaseDate(), firstMagazine.getReleaseDate());
        assertEquals(magazine.getAuthors(), firstMagazine.getAuthors());
    }

    @org.junit.Test
    public void testFindByISBNManyResult() throws Exception {
        Magazine magazine = new Magazine("", "545", authors, new Date());

        service.readFromCsvFile(Config.magazineFilePath, Constant.EURO_CHARSET, new MagazineParse());

        List<Publication> foundMagazines = service.findByISBN(magazine.getIsbn());

        assertEquals(3, foundMagazines.size());
    }

    @org.junit.Test
    public void testSortByTitle() throws Exception {
        service.add(magazine1);
        service.add(magazine2);
        service.add(magazine3);
        service.add(magazine4);
        service.sortByTitle();

        List<Magazine> magazines = new ArrayList<>();
        magazines.add(magazine3);
        magazines.add(magazine4);
        magazines.add(magazine2);
        magazines.add(magazine1);

        assertThat(service.findAll(), IsIterableContainingInOrder.contains(magazines.toArray()));
    }
}
