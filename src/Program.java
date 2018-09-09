import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("expression.txt");
        Scanner sc = new Scanner(inputFile);
        String input ;
        while( sc.hasNextLine() ){
            input = sc.nextLine();
            input = input.replaceAll(" ", "");
            Parser myParser = new Parser();
            Expression tree = myParser.parse(input);
            System.out.println(tree.calculate());
        }

    }
}
