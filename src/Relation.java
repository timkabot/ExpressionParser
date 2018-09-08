class Relation extends Expression {

    enum Opcode {
        less,greater,equal,none ;
        private int left_border, right_border;

        public String getOp() {
            switch (this) {
                case less: return "<";
                case greater: return ">";
                case equal:  return "=";
                default:  return "NULL";
            }
        }

        public int getLeft_border() {
            return left_border;
        }

        public void setLeft_border(int left_border) {
            this.left_border = left_border;
        }

        public int getRight_border() {
            return right_border;
        }

        public void setRight_border(int right_border) {
            this.right_border = right_border;
        }
    }

}
