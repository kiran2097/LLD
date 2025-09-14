package org.example.prototype;

public class Main {

    public static void main(String[] args) {
        Email email = new Email("Hello", "hello@world.com", "This is a test email.");
        Email emailCopy = email.copy();
        PremiumEmail pe1 = new PremiumEmail("Premium", "preHello@world.com", "This is a premium email.", "Premium Content");
        PremiumEmail pe2 = pe1.copy();
    }
}
