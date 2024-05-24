package mingati.luis.projectdb.model;

public class NeighborhoodSales {
    private int totalSales;
    private double totalValueSold;

    public NeighborhoodSales(int totalSales, double totalValueSold) {
        this.totalSales = totalSales;
        this.totalValueSold = totalValueSold;
    }

    // getters and setters
    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalValueSold() {
        return totalValueSold;
    }

    public void setTotalValueSold(double totalValueSold) {
        this.totalValueSold = totalValueSold;
    }
}
