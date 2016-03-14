html.html {
    head {
        title 'JVM Info'
        meta(content:'text/html')
    }
    body {

        h1 'System Properties'

        table(border: '1') {
            tr {
                th "Key"
                th "Value"
            }
            System.properties.each { k, v ->
                tr {
                    td "$k"
                    td "$v"
                }
            }
        }
    }
}

