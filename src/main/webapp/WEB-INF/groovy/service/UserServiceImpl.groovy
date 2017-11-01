package groovy.service

import wjw.test.springboot.service.IUserService

class UserServiceImpl implements IUserService {
	public String getUser() {
		return "你好,世界,Groovy Spring Boot!${new Date()}"
	}
}
