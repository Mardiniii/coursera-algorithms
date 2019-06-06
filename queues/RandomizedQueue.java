/* *****************************************************************************
 *  Name: Sebastian Zapata
 *  Date: 06/07/2019
 *  Description: A randomized queue is similar to a stack or queue, except that
 *  the item removed is chosen uniformly at random from items in the data
 *  structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

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

    // Add an item to the RandomizedQueue.
    public void enqueue(Item item) {
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    // Remove and return a random item from the RandomizedQueue.
    public Item dequeue(Item item) {
        // Rearrange elements of the RandomizedQueue in uniformly random order.
        queue = StdRandom.shuffle(queue);

        item = queue[--n];
        queue[n] = null; // avoid loitering

        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
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
