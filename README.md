#如何让Spring Boot 完美集成 Groovy

# 1. 修改`build.gradle`文件
用`apply plugin: 'groovy'`替换掉`apply plugin: 'java'`

# 2. 编写java接口类
在`src\main\java\`目录下  
> 例如:`src\main\java\wjw\test\springboot\service\IUserService.java`  
```
package wjw.test.springboot.service;

public interface IUserService {
	public String getUser();
}

```

# 3. 编写Groovy实现类
在`src\main\webapp\WEB-INF\groovy\`目录下  
> 例如: `src\main\webapp\WEB-INF\groovy\service\UserServiceImpl.groovy`
```
package groovy.service

import wjw.test.springboot.service.IUserService

class UserServiceImpl implements IUserService {
	public String getUser() {
		return "你好,世界,Groovy Spring Boot!"
	}
}

```  

# 4. 编写Spring Bean配置文件  
> 注意:需要采用`file:src/main/webapp/`作为前缀  
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://www.springframework.org/schema/beans" xmlns:lang="http://www.springframework.org/schema/lang"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
	                         http://www.springframework.org/schema/lang http://www.springframework.org/schema/lang/spring-lang-3.2.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
                           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
                           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.2.xsd">

    <lang:defaults refresh-check-delay="60000" />

    <!-- 用户服务 -->
    <lang:groovy id="userService"
      script-source="file:src/main/webapp/WEB-INF/groovy/service/UserServiceImpl.groovy">
    </lang:groovy>
</beans>
```

# 5. 编写`GroovyServletEx`类
```
package groovy.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "*.gdo", loadOnStartup = 2, initParams = { @WebInitParam(name = "verbose", value = "false"),
		@WebInitParam(name = "logGROOVY861", value = "true"),
		@WebInitParam(name = "resource.name.regex", value = "gdo$"),
		@WebInitParam(name = "resource.name.replacement", value = "groovy") })
public class GroovyServletEx extends GroovyServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding(this.encoding); // @wjw_note:
														// 解决form提交数据的编码问题!

		super.service(request, response);
	}

}

```

# 6. 修改Spring Boot启动文件,引入`GroovyServletEx`类
添加`@ServletComponentScan(basePackages=["groovy.servlet"])`  

# 7. 修改Spring Boot启动文件,引入xml配置文件
添加`@ImportResource(locations=["file:src/main/webapp/WEB-INF/service-manage.xml"])`  
注意:需要采用`file:src/main/webapp/`作为前缀    

# 8. 用gradle来启动应用
```
gradlew.bat bootRun -PappArgs="--spring.profiles.active=dev"
```
> 注意:不能采用WAR的方式在其他web容器下运行!
