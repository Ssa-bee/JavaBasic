package ch6;

public class ProductTest {
    public static void main(String[] args) {
        System.out.println("2");
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        System.out.println("p1.serialNo = " + p1.serialNo);
        System.out.println("p2.serialNo = " + p2.serialNo);
        System.out.println("p3.serialNo = " + p3.serialNo);

        System.out.println("Product.count = " + Product.count);
    }
}

class Product{
    static int count = 0;
    int serialNo;
    {
        ++count;
        serialNo = count;
    }

    public Product(){   }
}
