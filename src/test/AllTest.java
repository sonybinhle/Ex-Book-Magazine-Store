package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.repository.AuthorRepositoryTest;
import test.service.AuthorServiceTest;
import test.service.MagazineServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorRepositoryTest.class,
        AuthorServiceTest.class,
        MagazineServiceTest.class
})
public class AllTest {
}
