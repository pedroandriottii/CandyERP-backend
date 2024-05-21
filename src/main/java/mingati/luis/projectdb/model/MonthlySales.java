package mingati.luis.projectdb.model;


import java.math.BigDecimal;

public class MonthlySales {
    private int year;
    private int month;
    private BigDecimal totalRevenue;

    public MonthlySales(int year, int month, BigDecimal totalRevenue) {
        this.year = year;
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
