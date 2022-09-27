package numbers;

public class Parameters {
    private final Long startNumber;
    private final Long count;
    private final String[] props;

    public Parameters(Long startNumber, Long count, String[] props) {
        this.startNumber = startNumber;
        this.count = count;
        this.props = props;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public Long getCount() {
        return count;
    }

    public String[] getProps() {
        return props;
    }
}
