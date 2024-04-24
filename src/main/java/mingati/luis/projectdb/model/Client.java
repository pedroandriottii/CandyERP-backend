package mingati.luis.projectdb.model;

public class Client {
    private int id;
    private String name;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;

    public Client() {
    }

    public Client(int id, String name, String street, String number, String neighborhood, String complement) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}