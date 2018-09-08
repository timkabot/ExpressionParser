class Program {
    fun main(args: Array<String>) {
        val input = readLine() ?: ""
        val parser = Parser(input)
        val expressionTree = parser.parse()
    }
}