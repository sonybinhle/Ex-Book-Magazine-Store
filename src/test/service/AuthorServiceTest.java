package test.service;

import com.xeasony.Config;
import com.xeasony.model.Author;
import com.xeasony.repository.AuthorRepository;
import com.xeasony.repository.base.IRepository;
import com.xeasony.service.AuthorParse;
import com.xeasony.service.AuthorService;
import com.xeasony.service.base.IService;
import com.xeasony.util.Constant;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsSame;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorServiceTest {
    IService<Author> service;
    Author author1 = new Author("A", "1", "author1@gmail.com");
    Author author2 = new Author("A", "2", "author2@gmail.com");
    Author author3 = new Author("A", "3", "author3@gmail.com");
    Author author33 = new Author("A", "33", "author3@gmail.com");
    Author author4 = new Author("A", "4", "author4@gmail.com");

    @org.junit.Before
    public void initialize() {
        service = new AuthorService(new AuthorRepository());
    }

    public void initAdd() {
        service.add(author1);
        service.add(author2);
    }

    @org.junit.Test
    public void testFind() throws Exception {
        initAdd();
        Author findAuthor = new Author("", "", author2.getEmail());
        assertSame(author2, service.find(findAuthor));

        findAuthor = new Author("", "", author2.getEmail() + "x");
        assertEquals(null, service.find(findAuthor));
    }

    @org.junit.Test
    public void testFindAll() throws Exception {
        initAdd();

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        assertThat(service.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        initAdd();

        List<Author> authors = new ArrayList<>(service.findAll());

        authors.add(author3);
        service.add(author3);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));

        service.add(author33);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));

        authors.add(author4);
        service.add(author4);
        assertThat(service.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));
    }

    @org.junit.Test
    public void testReadFromCsv() throws Exception {
        Author author = new Author("Harald", "Rabe", "pr-rabe@optivo.de");
        service.readFromCsvFile(Config.authorFilePath, Constant.EURO_CHARSET, new AuthorParse());
        assertEquals(6, service.findAll().size());

        Author existed = service.find(author);

        assertEquals(author.getFirstname(), existed.getFirstname());
        assertEquals(author.getLastname(), existed.getLastname());
        assertEquals(author.getEmail(), existed.getEmail());
    }
}
