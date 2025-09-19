package org.example.builder;

public class Main {

    public static void main(String[] args) {
        User user = new User.Builder()
                .firstName("Kiran")
                .lastName("Kumari")
                .email("kiran@gmail.com")
                .phoneNumber("1234567890")
                .address("123 Main St")
                .build();
    }
}
