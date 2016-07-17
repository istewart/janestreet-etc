public enum Action {
    BUY {
        @Override
        public String toString() {
            return "BUY";
        }
    }, 
    SELL {
        @Override
        public String toString() {
            return "SELL";
        }
    },
    NONE {
        @Override
        public String toString() {
            return "NONE";
        }
    },
    CONVERT {
        @Override
        public String toString() {
            return "CONVERT";
        }
    }
}
