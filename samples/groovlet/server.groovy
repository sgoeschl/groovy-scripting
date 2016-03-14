#!/usr/bin/env groovy

import groovy.servlet.GroovyServlet
import org.mortbay.jetty.Server
import org.mortbay.jetty.servlet.*

@Grab(group = 'org.mortbay.jetty', module = 'jetty-embedded', version = '6.1.14')

def startJetty() {
    Server jetty = new Server(9090)
    Context context = new Context(jetty, '/', Context.SESSIONS)
    context.resourceBase = './scripts'
    context.addServlet(GroovyServlet, '*.groovy')
    context.setAttribute('version', '1.0')
    jetty.start()
}

println "Starting Jetty, press Ctrl+C to stop."
startJetty()
