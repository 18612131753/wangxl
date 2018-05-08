package gof.ray.Strategy.A4;

public class SortAndPrint {
    @SuppressWarnings("rawtypes")
    Comparable[] data;
    Sorter sorter;

    @SuppressWarnings("rawtypes")
    public SortAndPrint(Comparable[] data, Sorter sorter) {
        this.data = data;
        this.sorter = sorter;
    }

    public void execute() {
        print();
        sorter.sort(data);
        print();
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + ", ");
        }
        System.out.println("");
    }
}
