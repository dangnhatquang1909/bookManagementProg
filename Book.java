public class Book {
    int id;
    String name;
    double price;

    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //    collect ID
    public int colId() {
        return id;
    }

    //    collect Name
    public String colName() {
        return name;
    }

    //    collect Price
    public double colPrice() {
        return price;
    }

    /**
     * return this as a String in the required format
     */
    @Override
    public String toString() {
        return String.format("%5d %-45s %10.2f", id, name, price);
    }
}