package Registry.Counter;

public class Counter implements AutoCloseable{
    private int count;
    private boolean flag;

    public Counter(int count) {
        this.count = count;
        this.flag = true;
    }

    public void add() {
        if (flag)
            count++;
        else
            throw new RuntimeException();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void close() throws Exception {
        this.flag = false;
    }

    @Override
    public String toString() {
        return String.format("%d", this.count);
    }
}
