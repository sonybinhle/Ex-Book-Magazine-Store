package test.repository;

import com.xeasony.model.Author;
import com.xeasony.repository.AuthorRepository;
import com.xeasony.repository.base.IRepository;
import org.hamcrest.collection.IsIterableContainingInOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorRepositoryTest {
    IRepository<Author> repository;
    Author author1 = new Author("A", "1", "author1@gmail.com");
    Author author2 = new Author("A", "2", "author2@gmail.com");
    Author author3 = new Author("A", "3", "author3@gmail.com");
    Author author33 = new Author("A", "33", "author3@gmail.com");
    Author author4 = new Author("A", "4", "author4@gmail.com");

    @org.junit.Before
    public void initialize() {
        repository = new AuthorRepository();
        repository.add(author1);
        repository.add(author2);
    }

    @org.junit.Test
    public void testFind() throws Exception {
        Author findAuthor = new Author("", "", author2.getEmail());
        assertSame(author2, repository.find(findAuthor));

        findAuthor = new Author("", "", author2.getEmail() + "x");
        assertEquals(null, repository.find(findAuthor));
    }

    @org.junit.Test
    public void testFindAll() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        assertThat(repository.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        List<Author> authors = new ArrayList<>(repository.findAll());

        authors.add(author3);
        repository.add(author3);
        assertThat(repository.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));

        repository.add(author33);
        assertThat(repository.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));

        authors.add(author4);
        repository.add(author4);
        assertThat(repository.findAll(), IsIterableContainingInOrder.contains(authors.toArray()));
    }
}
