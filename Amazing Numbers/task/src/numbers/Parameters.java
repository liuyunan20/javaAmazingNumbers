package numbers;

public class Parameters {
    private Long startNumber;
    private Long count;
    private String property;
    public Parameters(Long startNumber, Long count, String property) {
        this.startNumber = startNumber;
        this.count = count;
        this.property = property;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
