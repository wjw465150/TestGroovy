package wjw.test.springboot

import org.apache.log4j.xml.DOMConfigurator
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment

@SpringBootApplication
@ServletComponentScan(basePackages=["groovy.servlet"])
class TestGroovyApplication implements EnvironmentAware {

	private Environment env;

	@Value('${logging.config}')
	private String loggingConfig;

	public static void main(String[] args) {
		SpringApplication.run TestGroovyApplication, args
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
		String[] dProfiles = env.getDefaultProfiles();
		String[] aProfiles = env.getActiveProfiles();

		System.out.println("Spring DefaultProfiles:" + java.util.Arrays.asList(dProfiles));
		System.out.println("Spring ActiveProfiles:" + java.util.Arrays.asList(aProfiles));
		if (aProfiles.length == 0) {
			System.out.println("Please set 'spring.profiles.active'");
			System.exit(-1);
		}

		//加载Log4J配置文件
		DOMConfigurator.configure(org.springframework.util.ResourceUtils.getURL(loggingConfig))
		Logger log = LoggerFactory.getLogger(this.getClass());

		log.info("当前ActiveProfiles:"+env.getActiveProfiles().toString());
	}
}
