#!/usr/bin/env groovy

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext

@Grab(group="org.apache.camel", module="camel-groovy", version="2.13.1")
@Grab(group="org.apache.camel", module="camel-jetty", version="2.13.1")
@Grab(group="org.slf4j", module="slf4j-jdk14", version="1.7.5")

class MyRouteBuilder extends RouteBuilder {
    int num = 0;
    def number = {++num};
    void configure() {
        from("jetty:http://0.0.0.0:8080/")
        .transform({"You called me ${number()} times"})
        .end()
    }
}

def camelContext = new DefaultCamelContext();
camelContext.addRoutes(new MyRouteBuilder())
camelContext.start();

System.in.read();
camelContext.stop();