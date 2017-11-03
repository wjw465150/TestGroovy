package wjw.test.springboot

import javax.servlet.annotation.WebServlet

import org.apache.log4j.xml.DOMConfigurator
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.ImportResource
import org.springframework.core.env.Environment

@SpringBootApplication
@ImportResource(locations=["file:src/main/webapp/WEB-INF/service-manage.xml"])
@ServletComponentScan(basePackages=["groovy.servlet"])  //@wjw_note 扫描@WebServlet注解的Servlet类
class TestGroovyApplication implements EnvironmentAware {
	@Value('${logging.config}')
	private String loggingConfig;

	public static void main(String[] args) {
		SpringApplication.run TestGroovyApplication, args
	}

	@Override
	public void setEnvironment(Environment env) {
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
