import org.apache.commons.cli.Option
import groovy.transform.ToString

static void main(String[] args) {
    println new CommandLine(args)
}

@ToString(includeNames = true)
class CommandLine {

    boolean verbose = false
    String site = 'local'
    String tenant = 'cz'
    List<String> files = new ArrayList()
    Set<String> products = ['accounts', 'cards', 'pensions', 'buildings', 'insurances', 'securities']

    CommandLine(String[] args) {

        def cli = new CliBuilder(usage: 'groovy export-webapi-user-accounts.groovy [options] file[s]', stopAtNonOption: false)
        cli.h(longOpt: 'help', 'usage information', required: false)
        cli.v(longOpt: 'verbose', 'verbose mode', required: false)

        cli.s(longOpt: 'site', 'site to use [local|dev|fat|uat|prod]', required: false, args: 1)
        cli.p(longOpt: 'products', 'products to fetch [accounts,cards,buildings,pensions,insurances,securities]', required: false, args: Option.UNLIMITED_VALUES, valueSeparator: ',')
        cli.t(longOpt: 'tenant', 'tenant to use [at|cz]', required: false, args: 1)

        OptionAccessor opt = cli.parse(args)

        if (!opt)  { System.exit(1) }
        if (opt.v) { this.verbose = true }

        if (opt.h) { cli.usage(); System.exit(0); }
        if (opt.o) { this.outputType = opt.o }
        if (opt.s) { this.site = opt.s }
        if (opt.t) { this.tenant = opt.t }
        if (opt.p) { this.products = opt.ps }

        if (opt.arguments()) {
            this.files = opt.arguments()
        }
        else {
            cli.usage()
        }
    }
}
