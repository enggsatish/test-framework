package hpe.tfm.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@EntityScan(basePackages={"hpe.tfm.dao.model"})
@EnableJpaRepositories("hpe.tfm.dao.repository")
@ComponentScan("hpe.tfm.dao")
public class DaoApplicationConfig {

}
