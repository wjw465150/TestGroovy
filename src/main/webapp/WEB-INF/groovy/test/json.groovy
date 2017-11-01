import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import groovy.json.*;
import wjw.test.springboot.service.IUserService

WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context)
IUserService us = appContext.getBean("userService")

response.contentType = 'application/json'

builder = new JsonBuilder()

builder.person {
	"name" '你好Hiarcs, Young Fey'
	"age" 31
	"pats" 'Congcong', 'wava',
	["1","2"]
}

//out << builder
out << us.getUser()

Logger log = LoggerFactory.getLogger(this.getClass());
log.info("你好json test");


