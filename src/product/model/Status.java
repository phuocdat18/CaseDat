package product.model;

public enum Status {
    SOLD("SOLD"), NOTSOLD("NOTSOLD");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
