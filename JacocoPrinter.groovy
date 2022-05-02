def reportFile = new File("target/site/jacoco/index.html")

if (!reportFile.exists() || !reportFile.canRead()) {
    println "[JacocoPrinter] Skipped due to missing report file."
    return
}

reportFile.withReader('UTF-8') { reader ->
    def html = getParser().parseText(reader.readLine())
    def totalRow = html.body.table.tfoot.tr
    def instructionMissed = totalRow.td[1]
    def instructionRatio = totalRow.td[2]
    def branchMissed = totalRow.td[3]
    def branchRatio = totalRow.td[4]
    println "[JacocoPrinter] Instructions  ${instructionRatio}  (Missed ${instructionMissed})"
    println "[JacocoPrinter] Branches      ${branchRatio}  (Missed ${branchMissed})"
}

XmlSlurper getParser() {
    parser = new XmlSlurper()
    parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
    return parser
}