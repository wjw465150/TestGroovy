package wjw.test.springboot

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment

@SpringBootApplication
@ServletComponentScan(basePackages=["groovy.servlet"])
class TestGroovyApplication implements EnvironmentAware {
	private static Logger log = LoggerFactory.getLogger(TestGroovyApplication.getClass());

	private Environment env;

	static void main(String[] args) {
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

		log.info("当前ActiveProfiles:"+env.getActiveProfiles().toString());
	}
}
