/**
 * Created by Timkabor on 10/10/2017.
 */
class Parenthesized extends Primary {
    private String input;

    Parenthesized(String input) {
        this.input = input.substring(1, input.length() - 1);
    }

    String getInput() {
        return input;
    }
}
