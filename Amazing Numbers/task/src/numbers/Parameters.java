package numbers;

import java.util.ArrayList;

public class Parameters {
    private final Long startNumber;
    private final Long count;
    private final ArrayList<String> props;

    public Parameters(Long startNumber, Long count, ArrayList<String> props) {
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

    public ArrayList<String> getProps() {
        return props;
    }
}
