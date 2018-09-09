class Parser {

    Parser() {
    }

    Expression parse(String input) {
        return parseRelation(input);
    }

    public Expression parseRelation(String input) {
        Expression answer = new Expression();
        Relation.Opcode op = findRelOp(input);
        if (op == Relation.Opcode.none)
            answer = parseTerm(input);
        else {
            answer.setNode(op.getOp());
            int r = op.getRight_border(), l = op.getLeft_border();
            answer.setLeftChild(parseRelation(input.substring(0, l)));
            answer.setRightChild(parseRelation(input.substring(r)));
        }
        return answer;
    }

    public Relation.Opcode findRelOp(String input) {

        Relation.Opcode answer = Relation.Opcode.none;
        long len = input.length(), counter = 0;
        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);

            //pass parenthesis
            if (ch == '(') {
                counter = 1;
                i++;
                while (counter > 0) {
                    if (input.charAt(i) == ')') counter--;
                    else if (input.charAt(i) == '(') counter++;
                    i++;
                }
            }
            if (i >= 0 && i < len) ch = input.charAt(i);
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

    public Expression parseTerm(String input) {

        Expression answer = new Expression();
        Term.Opcode op = findTermOp(input);
        if (op == Term.Opcode.none)
            answer = parseFactor(input);
        else {
            answer.setNode(op.getOp());
            int r = op.getRight_border(), l = op.getLeft_border();
            answer.setLeftChild(parseTerm(input.substring(0, l)));
            answer.setRightChild(parseTerm(input.substring(r)));

        }
        return answer;
    }

    public Term.Opcode findTermOp(String input) {
        Term.Opcode answer = Term.Opcode.none;
        int len = input.length(), counter = 0;
        for (int i = len-1; i >=0; i--) {
            char ch = input.charAt(i);

            if (ch == ')') {
                counter = 1;
                while (counter > 0) {
                    i--;
                    if (input.charAt(i) == '(') counter--;
                    if (input.charAt(i) == ')') counter++;
                }
            }
            if (i >= 0 && i < len) ch = input.charAt(i);

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

    public Expression parseFactor(String input) {

        Expression result = new Expression();
        Factor.Opcode op = findFacOp(input);
        if (op == Factor.Opcode.none)
            result = parsePrimary(input);
        else {
            result.setNode(op.getOp());
            int r = op.getRight_border(), l = op.getLeft_border();
            result.setLeftChild(parseFactor(input.substring(0, l)));
            result.setRightChild(parseFactor(input.substring(r)));
        }
        return result;
    }

    public Factor.Opcode findFacOp(String input) {
        Factor.Opcode answer = Factor.Opcode.none;
        long len = input.length(), counter = 0;
        for (int i = 0; i < len; i++) {

            char ch = input.charAt(i);

            if (ch == '(') {
                counter = 1;
                while (counter > 0) {
                    i++;
                    if (input.charAt(i) == ')') counter--;
                    if (input.charAt(i) == '(') counter++;
                }
            }
            if (i >= 0 && i < len) ch = input.charAt(i);

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

    public boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public boolean isInteger(String s, int radix) {
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

    public Expression parsePrimary(String input) {

        if(input.charAt(0) == '(') return parseParenthesized(input);
        return parseInteger(input);
    }

    public Expression parseInteger(String input) {
        return new Expression(input, null, null);
    }

    public Expression parseParenthesized(String input) {
        return parse(input.substring(1, input.length() - 1));
    }

}
