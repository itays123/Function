package axis;

/**
 * Represents a rectangle in the real plane
 */
public class Range {

    protected double start;
    protected double end;

    public Range() {
        this(0, 0);
    }

    public Range(double start, double end) {
        setRange(start, end);
    }

    public Range(Range other) {
        this(other.getStart(), other.getEnd());
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    /**
     * Expand the range to include the number x
     * 
     * @param x the number to include in the range
     * @return true if the range was modified, false othereise
     */
    public boolean expandTo(double x) {
        if (start > x) {
            start = x;
            return true;
        }
        if (end < x) {
            end = x;
            return true;
        }
        return false;
    }

    public double getLength() {
        return end - start;
    }

    public void setRange(double start, double end) {
        this.start = start;
        this.end = end;
    }

}
