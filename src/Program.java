import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("in.txt");
        Scanner sc = new Scanner(inputFile);
        String input;
        StringBuilder answer = new StringBuilder();
        Writer writer = new Writer();
        while( sc.hasNextLine() ){
            input = sc.nextLine();
            input = input.replaceAll(" ", "");
            Parser myParser = new Parser();
            Expression tree = myParser.parse(input);
            answer.append(tree.calculate() + "\n");
        }
        writer.write(answer.toString());
    }
}
