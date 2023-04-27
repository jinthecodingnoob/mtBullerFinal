public class Customer {

    private int custId;
    private String name;
    static int nextID = 100;

    public Customer () {}

    public Customer (String name) {
        this.name = name;
        custId = nextID++;
    }

    public int getCustId () {
        return custId;
    }
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String toString () {
        return "Customer ID: " + custId + ", name: " + name;
    }

}
