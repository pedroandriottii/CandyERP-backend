package mingati.luis.projectdb.model;

public class BestSellProducts {
    private int id;
    private String name;
    private int totalQuantitySold;

    public BestSellProducts(int id, String name, int totalQuantitySold) {
        this.id = id;
        this.name = name;
        this.totalQuantitySold = totalQuantitySold;
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

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }
}
