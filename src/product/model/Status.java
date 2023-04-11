package product.model;

public enum Status {
    INSTOCK("INSTOCK"), OUTOFSTOCK("OUTOFSTOCK");

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
