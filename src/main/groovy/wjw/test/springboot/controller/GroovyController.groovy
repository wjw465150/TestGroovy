package wjw.test.springboot.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController  //@wjw @Controller+@ResponseBody
class GroovyController {
	@RequestMapping("/")
	public String index() {
		return "测试Greetings from Spring Boot Groovy!";
	}
}
