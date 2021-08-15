// ID: 337914121

package arkanoid;

/**
 * a counter object.
 * simple class that is used for counting things
 */
public class Counter {
    private int count;

    /**
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * constructor.
     * @param num number to begin the counter
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * increase.
     * @param number number to add to current count
     * add number to current count.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * decrease.
     * substract number to current count.
     * @param number number subtract to current count
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get value.
     * @return this.count value of the counter
     */
    public int getValue() {
        return this.count;
    }
}
