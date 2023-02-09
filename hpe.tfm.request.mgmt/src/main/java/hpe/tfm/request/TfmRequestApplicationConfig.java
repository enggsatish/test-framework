package hpe.tfm.request;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hpe.tfm"})
public class TfmRequestApplicationConfig extends TfmRequestMgmtConfig {

}
