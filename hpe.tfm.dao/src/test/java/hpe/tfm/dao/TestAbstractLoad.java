package hpe.tfm.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )	
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@ContextConfiguration(classes = DaoApplicationConfig.class)
public abstract class TestAbstractLoad {

	
	@SpringBootApplication
	static class TestConfiguration {
	}
}
