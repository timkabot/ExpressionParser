class Parser {

    Parser() {
    }

    Expression parse(String input) {
        return parseRelation(input);
    }

    private Expression parseRelation(String input) {
        Expression answer = new Expression();
        Relation.Opcode op = findRelOp(input);
        if (op == Relation.Opcode.none)
            answer = parseTerm(input);
        else {
            answer.setNode(op.getOp());

            answer.setLeftChild(parseRelation(input.substring(0, op.getLeft_border())));
            answer.setRightChild(parseRelation(input.substring(op.getRight_border())));
        }
        return answer;
    }

    private Relation.Opcode findRelOp(String input) {
        Relation.Opcode answer = Relation.Opcode.none;
        int len = input.length();
        for (int i = 0; i < len - 1; i++) {
            char ch = input.charAt(i);

            if (ch == '(')
                while (input.charAt(i) != ')')
                    i++;

            if (ch == '<') {
                answer = Relation.Opcode.less;
                answer.setLeft_border(i);
                answer.setRight_border(i + 1);
                break;
            }

            if (ch == '>') {
                answer = Relation.Opcode.greater;
                answer.setLeft_border(i);
                answer.setRight_border(i + 1);
                break;
            }

            if (ch == '=') {
                answer = Relation.Opcode.equal;
                answer.setLeft_border(i);
                answer.setRight_border(i + 1);
                break;
            }
        }
        return answer;
    }

    private Expression parseTerm(String input) {
        Expression answer = new Expression();
        Term.Opcode op = findTermOp(input);
        if (op == Term.Opcode.none)
            answer = parseFactor(input);
        else {
            answer.setNode(op.getOp());

            answer.setLeftChild(parseTerm(input.substring(0, op.getLeft_border())));
            answer.setRightChild(parseTerm(input.substring(op.getRight_border())));
        }
        return answer;
    }

    private Term.Opcode findTermOp(String input) {
        Term.Opcode answer = Term.Opcode.none;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(')
                while (input.charAt(i) != ')')
                    i++;

            switch (ch) {
                case '+':
                    answer = Term.Opcode.plus;
                    answer.setLeft_border(i);
                    answer.setRight_border(i + 1);
                    return answer;

                case '-':
                    answer = Term.Opcode.minus;
                    answer.setLeft_border(i);
                    answer.setRight_border(i + 1);
                    return answer;

            }

        }
        return answer;
    }

    private Expression parseFactor(String input) {
        Expression result = new Expression();
        Factor.Opcode op = findFacOp(input);
        if (op == Factor.Opcode.none)
            result = parsePrimary(input);
        else {
            result.setNode(op.getOp());
            result.setLeftChild(parseFactor(input.substring(0, op.getLeft_border())));
            result.setRightChild(parseFactor(input.substring(op.getRight_border())));
        }
        return result;
    }

    private Factor.Opcode findFacOp(String input) {
        Factor.Opcode answer = Factor.Opcode.none;
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);

            if (ch == '(')
                while (input.charAt(i) != ')')
                    i++;

            switch (ch) {
                case '*':
                    answer = Factor.Opcode.multiplication;
                    answer.setLeft_border(i);
                    answer.setRight_border(i + 1);
                    return answer;
            }
        }
        return answer;
    }

    private boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    private boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    private Expression parsePrimary(String input) {
        if (isInteger(input)) return parseInteger(input);
        return parseParenthesized(input);
    }

    private Expression parseInteger(String input) {
        return new Expression(input, null, null);
    }

    private Expression parseParenthesized(String input) {
        Parenthesized parenthesized = new Parenthesized(input);
        return parse(parenthesized.getInput());
    }

}
