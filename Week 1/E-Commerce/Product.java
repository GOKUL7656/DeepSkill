
import java.util.Arrays;
import java.util.Comparator;

class Product {
    private String id;
    private String name;
    private String cat;
    private double price;

    public Product(String id, String name, String cat, double price) {
        this.id = id;
        this.name = name;
        this.cat = cat;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCat() {
        return cat;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Product [ID=" + id + ", Name=" + name + ", Category=" + cat + ", Price=" + price + "]";
    }
}

class SearchAlgorithms {

    public static Product linearSearch(Product[] arr, String targetId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getId().equals(targetId)) {
                return arr[i];
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] arr, String targetId) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int cmp = targetId.compareTo(arr[mid].getId());

            if (cmp == 0) {
                return arr[mid];
            } else if (cmp < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P003", "Laptop Pro", "Electronics", 1200.00),
            new Product("P001", "Smartphone X", "Electronics", 800.00),
            new Product("P005", "Desk Chair", "Furniture", 150.00),
            new Product("P002", "Wireless Mouse", "Accessories", 25.00),
            new Product("P004", "External SSD", "Storage", 100.00)
        };

        System.out.println("--- Linear Search ---");
        String linearId = "P002";
        Product foundL = linearSearch(products, linearId);
        if (foundL != null) {
            System.out.println("Found: " + foundL);
        } else {
            System.out.println("Product " + linearId + " not found.");
        }

        String noIdL = "P999";
        Product noFoundL = linearSearch(products, noIdL);
        if (noFoundL != null) {
            System.out.println("Found: " + noFoundL);
        } else {
            System.out.println("Product " + noIdL + " not found.");
        }
        System.out.println();

        System.out.println("--- Binary Search ---");
        Arrays.sort(products, Comparator.comparing(Product::getId));

        String binaryId = "P004";
        Product foundB = binarySearch(products, binaryId);
        if (foundB != null) {
            System.out.println("Found: " + foundB);
        } else {
            System.out.println("Product " + binaryId + " not found.");
        }

        String noIdB = "P006";
        Product noFoundB = binarySearch(products, noIdB);
        if (noFoundB != null) {
            System.out.println("Found: " + noFoundB);
        } else {
            System.out.println("Product " + noIdB + " not found.");
        }
    }
}
