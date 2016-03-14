def config = new ConfigSlurper('production').parse('''  
website {
 url = "http://default.mycompany.com"
 port = 80
 user = "test"
}
 
environments {
 development {
  website {
   url = "http://dev.mycompany.com"
   port = 8080
  }
 }
 production {
  website {
   url = "http://www.mycompany.com"
   user = "prodUser"
  }
 }
}
''')

assert config.website.url == "http://www.mycompany.com"
assert config.website.port == 80
assert config.website.user == "prodUser"