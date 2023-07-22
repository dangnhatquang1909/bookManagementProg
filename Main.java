import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Book> bookList;
    static Scanner scanner = new Scanner(System.in);
    static BookManager bookManager = new BookManager();

    public static void main(String[] args) {
//  Whenever starting the program, it reads the file then parses the data for Book objects and add to the list of
//books.
        bookList = new ArrayList<>();
        scanner = new Scanner(System.in);
        int choose;
        do {
            try {
                System.out.println("-----------------------------------");
                System.out.println("1. list all books\n2. add a new book");
                System.out.println("3. edit book\n4. delete a book");
                System.out.println("5. search books by name");
                System.out.println("6. sort books descending by price\n");
                System.out.println("0. save & exit");
                System.out.println("-----------------------------------");
                System.out.print("Your option: ");

                choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {

//              When user choose '1'. List all books...
                    case 1:
                        bookManager.printBooks(bookManager.getBooks());
                        break;
//              When user choose '2'. Add a new book
                    case 2:
                        bookManager.input();
                        break;
//              When user choose'3'. Edit book...
                    case 3:
                        try {
                            System.out.print("Enter book ID: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            if (id <= 0) {
                                System.out.println("Please enter a positive number");
                            }
                            Book b = bookManager.getBookById(id);
                            if (b == null) {
                                break;
                            } else {
                                System.out.print("Enter book name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter book price: ");
                                double price = scanner.nextDouble();
                                scanner.nextLine();
                                if (price < 0) {
                                    price = 0;
                                }
                                b.setName(name);
                                b.setPrice(price);
                                System.out.println("Updated successfully.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                        }
                        break;
//              When user choose '4'. Delete a book...
                    case 4:
                        try {
                            System.out.print("Enter book ID: ");
                            int id = Integer.parseInt(scanner.nextLine());
                            Book b = bookManager.getBookById(id);
                            if (b == null) {
                                break;
                            } else {
                                bookManager.remove(b);
                                System.out.println("Deleted successfully.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid ID");
                        }
                        break;
//              When user choose '5'. Search book by name...
                    case 5:
                        System.out.print("Enter keyword: ");
                        String keyword = scanner.nextLine().toLowerCase();
                        bookManager.searchByName(keyword);
                        break;
//              When user choose '6'. Sort books descending by price...
                    case 6:
                        bookManager.sortDescByPrice();
                        break;
//              When user choose '0'. Save and Quit...
                    case 0:
                        bookManager.saveToFile();
                        System.out.println("Saving to file...\nBye!");
                        System.exit(0);
                        break;
//              If user DO NOT enter number from 0 to 6 => Invalid choice...
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        } while (true);
    }
}