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
