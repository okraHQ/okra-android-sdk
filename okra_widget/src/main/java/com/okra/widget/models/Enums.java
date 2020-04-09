package com.okra.widget.models;

public class Enums {

    public enum Environment {
        dev ("dev"),
        sandbox ("sandbox"),
        staging ("staging"),
        production ("production"),
        production_sandbox ("production-sandbox");

        private final String name;

        private Environment(String s) {
            name = s;
        }
        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return name.equals(otherName);
        }
        public String toString() {
            return this.name;
        }
    }

    public enum Product { auth, transactions, balance, identity}
}
