package main;

public class Session {
    private static boolean loggedIn = false;

    // This must match exactly what you call in Login.java
    public static void login() {
        loggedIn = true;
    }

    public static void logout() {
        loggedIn = false;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }
}