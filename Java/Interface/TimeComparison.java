package Interface;

public class TimeComparison implements Comparison {
    @Override
    public boolean isGreater(Time t1, Time t2) {
        return t1.toMinutes() > t2.toMinutes();
    }

    @Override
    public boolean isLess(Time t1, Time t2) {
        return t1.toMinutes() < t2.toMinutes();
    }

    @Override
    public boolean isEqual(Time t1, Time t2) {
        return t1.toMinutes() == t2.toMinutes();
    }
}
