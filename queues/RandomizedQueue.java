/* *****************************************************************************
 *  Name: Sebastian Zapata
 *  Date: 06/07/2019
 *  Description: A randomized queue is similar to a stack or queue, except that
 *  the item removed is chosen uniformly at random from items in the data
 *  structure.
 **************************************************************************** */

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue = (Item[]) new Object[1];
    private int n = 0;

    // Returns `true if the RandomizedQueue does not have any nodes, otherwise
    // this method returns `false`.
    public boolean isEmpty() { return n == 0; }

    // Returns the number of items in the RandomizedQueue.
    public int size() {
        return n;
    }

    // Resize the array to the given `max` size passed as an argument.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public static void main(String[] args) {

    }
}
