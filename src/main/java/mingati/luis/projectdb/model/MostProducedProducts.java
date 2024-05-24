package mingati.luis.projectdb.model;

public class MostProducedProducts {
    private int id;
    private String name;
    private int quantity;

    public MostProducedProducts(int id, String name, int totalQuantityProduced) {
        this.id = id;
        this.name = name;
        this.quantity = totalQuantityProduced;
    }

    // Getters and setters
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

    public int getTotalQuantityProduced() {
        return quantity;
    }

    public void setTotalQuantityProduced(int totalQuantityProduced) {
        this.quantity = totalQuantityProduced;
    }
}
