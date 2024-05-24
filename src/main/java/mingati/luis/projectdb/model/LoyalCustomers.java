package mingati.luis.projectdb.model;

public class LoyalCustomers {
    private int id;
    private String name;
    private int totalOrders;

    public LoyalCustomers(int id, String name, int totalOrders) {
        this.id = id;
        this.name = name;
        this.totalOrders = totalOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
