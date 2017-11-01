package wjw.test.springboot.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController  //@wjw @Controller+@ResponseBody
class GroovyController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String index() {
	    log.info("home test");
		return "测试Greetings from Spring Boot Groovy!";
	}
}
