package product.model;

public class Product {
    private int id;
    private PType type;
    private String name;
    private int quantity;
    private long price;
    private String description;
    private Status status;

    public Product() {
    }

    public Product(int id, PType type, String name, int quantity, long price, String description, Status status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public Product(String raw) {
        String [] strings = raw.split(";");
        this.id = Integer.parseInt(strings[0]);
        this.type = PType.valueOf(strings[1]);
        this.name = strings[2];
        this.quantity = Integer.parseInt(strings[3]);
        this.price = Long.parseLong((strings[4]));
        this.description = strings[5];
        this.status = Status.valueOf(strings[6]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PType getType() {
        return type;
    }

    public void setType(PType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ";" + type + ";" + name + ";" + quantity + ";" + price + ";" + description + ";" + status;
    }
}
