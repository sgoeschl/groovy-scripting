@Grab(group="com.jayway.jsonpath", module="json-path", version="2.1.0")
@Grab(group="org.codehaus.groovy.modules.http-builder", module="http-builder", version ="0.7.1")
@Grab(group="org.slf4j", module="slf4j-simple", version="1.6.1")

import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT
import static groovyx.net.http.ContentType.JSON

import com.jayway.jsonpath.JsonPath;
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient

// Use the dedicated RESTClient to get textual JSON to be accessed with JsonPath

def restClient = new RESTClient( "https://api.github.com" )
restClient.ignoreSSLIssues()

restClient.request(GET, TEXT) { req ->
  uri.path = '/users' 
  headers.'Accept' = 'application/json'
  headers.'User-Agent' = 'Mozilla/5.0' 

  response.success = { resp, reader ->
    println JsonPath.read(reader.text, "\$[*].login")
  }
}

// Use the HTTPCLient to get textual JSON to be processed with JsonPath

def httpBuilder = new HTTPBuilder( "https://api.github.com" )
httpBuilder.ignoreSSLIssues()

httpBuilder.request(GET,TEXT) { req ->
  uri.path = '/users'
  headers.'Accept' = 'application/json'
  headers.'User-Agent' = 'Mozilla/5.0'

  response.success = { resp, reader ->
    println JsonPath.read(reader.text, "\$[*].login")
  }
}

// Use the dedicated HTTPCLient with built-in GJSON

def restClient2 = new RESTClient( "https://api.github.com" )
restClient2.ignoreSSLIssues()

restClient2.request(GET,JSON) { req ->
  uri.path = '/users'
  headers.'User-Agent' = 'Mozilla/5.0'

  response.success = { resp, json ->
    for (record in json) {
       println record.login
    }
  }
}
