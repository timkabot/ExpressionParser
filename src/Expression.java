class Expression {
    private String node;
    private Expression leftChild, rightChild;

    Expression() {
    }

    Expression(String node, Expression leftChild, Expression rightChild) {
        this.node = node;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    String getNode() {
        return node;
    }

    void setNode(String node) {
        this.node = node;
    }

    Expression getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Expression leftChild) {
        this.leftChild = leftChild;
    }

    Expression getRightChild() {
        return rightChild;
    }

    void setRightChild(Expression rightChild) {
        this.rightChild = rightChild;
    }

    long calculate() {
        return solve(this);
    }

    private long solve(Expression expression) {
        switch (expression.node) {
            case "<":
                return solve(expression.leftChild) < solve(expression.rightChild) ? 1 : 0;
            case ">":
                return solve(expression.leftChild) > solve(expression.rightChild) ? 1 : 0;
            case "+":
                return solve(expression.leftChild) + solve(expression.rightChild);
            case "-":
                return solve(expression.leftChild) - solve(expression.rightChild);
            case "*":
                return solve(expression.leftChild) * solve(expression.rightChild);
            case "=":
                 if(solve(expression.leftChild) == solve(expression.rightChild)) return 1;
                 else return 0;
            default:
                return java.lang.Integer.valueOf(expression.node);
        }

    }
}
