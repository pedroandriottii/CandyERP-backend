package mingati.luis.projectdb.model;

public class ProductsByPaymentMethod {
    private double totalValueSold;

    public ProductsByPaymentMethod( double totalValueSold) {
        this.totalValueSold = totalValueSold;
    }

    public double getTotalQuantitySold() {
        return totalValueSold;
    }

    public void setTotalQuantitySold(double totalValueSold) {
        this.totalValueSold = totalValueSold;
    }
}
