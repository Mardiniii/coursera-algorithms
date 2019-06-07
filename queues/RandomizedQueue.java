/* *****************************************************************************
 *  Name: Sebastian Zapata
 *  Date: 06/07/2019
 *  Description: A randomized queue is similar to a stack or queue, except that
 *  the item removed is chosen uniformly at random from items in the data
 *  structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        int randomIndex = StdRandom.uniform(n);

        item = queue[randomIndex];

        int i = 0;
        for (int j = 0; j < n; j++) {
            if (j != randomIndex)
                queue[i++] = queue[j];
        }

        queue[n] = null; // avoid loitering
        n--;

        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    // Return a random item (but do not remove it)
    public Item sample() {
        int randomIndex = StdRandom.uniform(n);

        return queue[randomIndex];
    }

    // Resize the array to the given `max` size passed as an argument.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];

        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int[] indices;
        private int i = 0;

        public ListIterator() {
            indices = new int[n];

            for (int i = 0; i < n; i++)
                indices[i] = i;

            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not implemented.");
        }

        public Item next() {
            return queue[indices[i++]];
        }
    }

    public static void main(String[] args) {

    }
}
