package unit3.consoleBased;

//CRUD operation in a single file:
// https://github.com/AnkitaK65/Programming-in-Java/blob/main/src/main/java/org/AnkitaK65/jdbc/JdbcExample.java

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO dao = new BookDAO();

        while (true) {
            System.out.println("\n--- Book Catalog CRUD ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book Title");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (ch) {
                case 1:
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Author: "); String author = sc.nextLine();
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Genre: "); String genre = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble(); sc.nextLine();
                    System.out.print("Publish Date (yyyy-mm-dd): "); String date = sc.nextLine();
                    System.out.print("Description: "); String desc = sc.nextLine();
                    System.out.print("Rating: "); double rating = sc.nextDouble();
                    System.out.print("Pages: "); int pages = sc.nextInt(); sc.nextLine();
                    System.out.print("Language: "); String lang = sc.nextLine();

                    Book book = new Book(id, author, title, genre, price, date, desc, rating, pages, lang);
                    dao.insertBook(book);
                    break;

                case 2:
                    dao.getAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to update: ");
                    String uid = sc.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();
                    dao.updateBookTitle(uid, newTitle);
                    break;

                case 4:
                    System.out.print("Enter Book ID to delete: ");
                    String did = sc.nextLine();
                    dao.deleteBook(did);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

