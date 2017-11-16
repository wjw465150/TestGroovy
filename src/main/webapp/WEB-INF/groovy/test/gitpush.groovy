import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import groovy.json.*;

WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context)

response.contentType = 'text/plain'
out << "OK"

System.out.println("request属性:");
System.out.println("getAuthType( ):${request.getAuthType()}");
System.out.println("getProtocol( ):${request.getProtocol()}");
System.out.println("getMethod( ):${request.getMethod()}");
System.out.println("getScheme( ):${request.getScheme()}");
System.out.println("getContentType( ):${request.getContentType()}");
System.out.println("getContentLength( ):${request.getContentLength()}");
System.out.println("getCharacterEncoding( ):${request.getCharacterEncoding()}");
System.out.println("getRequestedSessionId( ):${request.getRequestedSessionId()}");
System.out.println("getContextPath( ):${request.getContextPath()}");
System.out.println("getServletPath( ):${request.getServletPath()}");
System.out.println("getPathInfo( ):${request.getPathInfo()}");
System.out.println("getRequestURI( ):${request.getRequestURI()}");
System.out.println("getRequestURL( ):${request.getRequestURL()}");
System.out.println("getQueryString( ):${request.getQueryString()}");

def p = request.getParameterMap().collectEntries { key, value ->
	[key, value[0]]
}
System.out.println("getParameterMap():"+p);
