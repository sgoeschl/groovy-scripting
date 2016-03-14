def cli = new CliBuilder(usage: 'groovy cli.groovy -s[dh] "server"')
cli.h(longOpt: 'help', 'usage information', required: false)
cli.s(longOpt: 'server', 'server to connect to', required: true, args: 1)
cli.d(longOpt: 'debug', 'enable debugging', required: false)

OptionAccessor opt = cli.parse(args)