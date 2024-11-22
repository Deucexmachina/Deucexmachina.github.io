package Interface;

public class Main {
    public static void main(String[] args) {
        Time time1 = new Time(10, 30);
        Time time2 = new Time(12, 15);

        Comparison comparison = new TimeComparison();

        System.out.println("Is time1 greater than time2? " + comparison.isGreater(time1, time2));
        System.out.println("Is time1 less than time2? " + comparison.isLess(time1, time2));
        System.out.println("Is time1 equal to time2? " + comparison.isEqual(time1, time2));
    }
}

