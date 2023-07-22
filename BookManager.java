import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    int id;
    String name;
    double price;
    Scanner scanner;
    ArrayList<Book> totalBooks = new ArrayList<>();

    public BookManager() {
        loadFromFile();
    }

    public ArrayList<Book> getBooks() {
        return totalBooks;
    }

    public void loadFromFile() {
        System.out.println("Loading books...");
//        Read the file "book.txt"
        try {
            File myObj = new File("books.txt");
            scanner = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred!\n Check your file again.");
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.isEmpty()) {
                continue;
            }
            int id = Integer.parseInt(nextLine.substring(0, 5).trim());
            String name = nextLine.substring(6, 51).trim();
            double price = Double.parseDouble(nextLine.substring(51).trim());
            Book bb = new Book(id, name, price);
            totalBooks.add(bb);
        }
    }

    public void printBooks(ArrayList<Book> books) {
        if (totalBooks.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.format("%-5s %-45s %-10s\n", "ID", "Name", "Price");
            for (Book totalBook : totalBooks) {
                int output_id = totalBook.colId();
                String output_name = totalBook.colName();
                double output_price = totalBook.colPrice();
                System.out.format("%5d %-45s %10.2f\n", output_id, output_name, output_price);
            }
        }
    }

    public boolean add(Book book) {
        int cur_id = book.id;
        for (Book totalBook : totalBooks) {
            if (cur_id == totalBook.id) {
                System.out.println("Duplicated ID!");
                return false;
            }
        }
        totalBooks.add(book);
        System.out.println("Added successfully.");
        return false;
    }

    public void input() {
        Scanner sc_id = new Scanner(System.in);
        System.out.print("Enter book id: ");
        id = sc_id.nextInt();
        if (id <= 0) {
            System.out.println("Please enter a positive number");
        } else {
            Scanner sc_name = new Scanner(System.in);
            System.out.print("Enter book name: ");
            name = sc_name.nextLine();
            Scanner sc_pri = new Scanner(System.in);
            System.out.print("Enter book price: ");
            price = sc_pri.nextDouble();
            if (price < 0) {
                price = 0;
            }
            Book book = new Book(id, name, price);
            add(book);
        }
    }

    public Book getBookById(int id) {
        for (Book book : totalBooks) {
            if (book.id == id) {
                return book;
            }
        }
        System.out.println("Invalid ID!");
        return null;
    }

    public void remove(Book book) {
        totalBooks.remove(book);
    }

    public void sortDescByPrice() {
        ArrayList<Book> sortDescByPrice = new ArrayList<>();
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < totalBooks.size() - 1; i++) {
                if (totalBooks.get(i).price < totalBooks.get(i + 1).price) {
                    swapped = true;
                    Book temp = totalBooks.get(i);
                    totalBooks.set(i, totalBooks.get(i + 1));
                    totalBooks.set(i + 1, temp);
                }
            }
        }
        if (!getBooks().isEmpty()) {
            System.out.println("After sorting:");
            System.out.format("%-5s %-45s %-10s %n", "ID", "Name", "Price");
            for (Book totalBook : totalBooks) {
                System.out.println(totalBook);
            }
        } else {
            System.out.println("(Empty)");
        }
    }

    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        for (Book totalBook : totalBooks) {
            String name = totalBook.name.toLowerCase();
            if (name.contains(keyword.toLowerCase())) {
                matches.add(totalBook);
            }
        }
        if (matches.isEmpty()) {
            System.out.println("(empty)");
        } else {
            System.out.format("%-5s %-45s %-10s %n", "ID", "Name", "Price");
            for (Book match : matches) {
                System.out.println(match);
            }
        }
        return matches;
    }

    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("books.txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            for (Book book : totalBooks) {
                writer.write(book.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}