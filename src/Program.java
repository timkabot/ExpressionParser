import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("expression.txt");
        Scanner sc = new Scanner(inputFile);
        String input = sc.nextLine();// 1+(26-98)<28

        Parser myParser = new Parser();
        Expression tree = myParser.parse(input);
        tree.calculate();
    }
}
