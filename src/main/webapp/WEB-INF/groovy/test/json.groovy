import groovy.json.*;

response.contentType = 'application/json'

builder = new JsonBuilder()

builder.person {
	"name" '你好Hiarcs, Young Fey'
	"age" 31
	"pats" 'Congcong', 'wava',
	["1","2"]
}

out << builder