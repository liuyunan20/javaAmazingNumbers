package numbers;

public class Parameters {
    private final Long startNumber;
    private final Long count;
    private final String property1;
    private final String property2;
    public Parameters(Long startNumber, Long count, String property1, String property2) {
        this.startNumber = startNumber;
        this.count = count;
        this.property1 = property1;
        this.property2 = property2;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public Long getCount() {
        return count;
    }

    public String getProperty1() {
        return property1;
    }

    public String getProperty2() {
        return property2;
    }
}
