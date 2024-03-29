/* *****************************************************************************
 *  Name: Sebastian Zapata
 *  Date: 06/07/2019
 *  Description: A randomized queue is similar to a stack or queue, except that
 *  the item removed is chosen uniformly at random from items in the data
 *  structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (item == null) {
            throw new IllegalArgumentException("Invalid item to be inserted!");
        }

        if (n == queue.length) resize(2 * queue.length);
        queue[n] = item;
        n++;
    }

    // Remove and return a random item from the RandomizedQueue.
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The RandomizedQueue is empty!");
        }

        // Rearrange elements of the RandomizedQueue in uniformly random order.
        int randomIndex = StdRandom.uniform(n);

        Item item = queue[randomIndex];
        queue[randomIndex] = queue[n-1];
        queue[n-1] = null;
        n--;

        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
    }

    // Return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("The RandomizedQueue is empty!");
        }

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

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private final int[] indices;
        private int i = 0;

        private ListIterator() {
            indices = new int[n];

            for (int j = 0; j < n; j++)
                indices[j] = j;

            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not implemented.");
        }

        public Item next() {
            if (i >= n) {
                throw new NoSuchElementException("No more items to be returned.");
            }

            return queue[indices[i++]];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();

        System.out.println("Running methods when the RandomizedQueue is empty...");
        randomQueue.enqueue(64);
        System.out.println("Randomized Queue size: " + randomQueue.size());
        randomQueue.dequeue();

        randomQueue.size();
        randomQueue.enqueue(0);
        randomQueue.enqueue(3);
        randomQueue.dequeue();

        System.out.println("Enqueueing 10 elements...");

        // Enqueue 10 elements
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        randomQueue.enqueue(4);
        randomQueue.enqueue(5);
        randomQueue.enqueue(6);
        randomQueue.enqueue(7);
        randomQueue.enqueue(8);
        randomQueue.enqueue(9);
        randomQueue.enqueue(10);

        System.out.println("Randomized Queue size: " + randomQueue.size());

        System.out.println("Sample 5 elements...");

        System.out.println("Sample item: " + randomQueue.sample());
        System.out.println("Sample item: " + randomQueue.sample());
        System.out.println("Sample item: " + randomQueue.sample());
        System.out.println("Sample item: " + randomQueue.sample());
        System.out.println("Sample item: " + randomQueue.sample());

        System.out.println("Dequeueing 10 elements...");

        // Dequeue 10 element
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();

        System.out.println("Randomized Queue size: " + randomQueue.size());

        System.out.println("Re-enqueueing 10 elements...");

        // Renqueue 10 elements
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        randomQueue.enqueue(4);
        randomQueue.enqueue(5);
        randomQueue.enqueue(6);
        randomQueue.enqueue(7);
        randomQueue.enqueue(8);
        randomQueue.enqueue(9);
        randomQueue.enqueue(10);

        Iterator<Integer> i = randomQueue.iterator();

        System.out.println("Randomized Queue size: " + randomQueue.size());

        while (i.hasNext()) {
            int n = i.next();
            System.out.println(n);
        }
    }
}
