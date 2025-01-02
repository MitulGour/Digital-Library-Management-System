package NestedLoops.No;

import java.util.*;

class Admin {
    private String adminId = "admin";
    private String adminPassword = "admin123";

    public boolean authenticate(String id, String password) {
        return adminId.equals(id) && adminPassword.equals(password);
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
    }
}

class Library {
    private Map<Integer, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added successfully.");
    }

    public void deleteBook(int bookId) {
        if (books.remove(bookId) != null) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book ID not found.");
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            books.values().forEach(System.out::println);
        }
    }
}

class User {
    private String userId;
    private String email;

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public void browseBooks(Library library) {
        library.listBooks();
    }

    public void query(String query) {
        System.out.println("Query sent via email: " + query);
    }
}

public class DigitalLibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Admin admin = new Admin();

        System.out.println("Welcome to the Digital Library Management System!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Access");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin ID: ");
                    String adminId = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String adminPassword = scanner.nextLine();

                    if (admin.authenticate(adminId, adminPassword)) {
                        System.out.println("Admin Login Successful!");
                        while (true) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Add Book");
                            System.out.println("2. Delete Book");
                            System.out.println("3. List Books");
                            System.out.println("4. Logout");
                            System.out.print("Enter your choice: ");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Enter Book ID: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    System.out.print("Enter Book Title: ");
                                    String title = scanner.nextLine();
                                    System.out.print("Enter Author Name: ");
                                    String author = scanner.nextLine();
                                    library.addBook(new Book(id, title, author));
                                    break;
                                case 2:
                                    System.out.print("Enter Book ID to Delete: ");
                                    int bookId = scanner.nextInt();
                                    library.deleteBook(bookId);
                                    break;
                                case 3:
                                    library.listBooks();
                                    break;
                                case 4:
                                    System.out.println("Admin logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Try again.");
                            }
                            if (adminChoice == 4) break;
                        }
                    } else {
                        System.out.println("Invalid Admin credentials.");
                    }
                    break;
                case 2:
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    User user = new User(userId, email);
                    while (true) {
                        System.out.println("\nUser Menu:");
                        System.out.println("1. Browse Books");
                        System.out.println("2. Query Library");
                        System.out.println("3. Logout");
                        System.out.print("Enter your choice: ");

                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (userChoice) {
                            case 1:
                                user.browseBooks(library);
                                break;
                            case 2:
                                System.out.print("Enter your query: ");
                                String query = scanner.nextLine();
                                user.query(query);
                                break;
                            case 3:
                                System.out.println("User logged out.");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                        if (userChoice == 3) break;
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
