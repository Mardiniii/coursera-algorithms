/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/04/2019
 *  Description: Deque is a generalization of a stack and a queue that supports
 *  adding and removing items from either the front or the back of the data
 *  structure.
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    // Nested class to define nodes
    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // Returns `true if the Deque does not have any nodes otherwise this method
    // returns `false`.
    public boolean isEmpty() {
        return first == null && last == null;
    }

    // Returns the number of items in the Deque.
    public int size() {
        return n;
    }

    // Add the item to front of the Deque.
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Invalid item to be inserted!");
        }

        Node oldFirst = first;
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.previous = null;

        if (isEmpty()) {
            newFirst.next = null;
            first = last = newFirst;
        } else {
            newFirst.next = oldFirst;
            oldFirst.previous = newFirst;
            first = newFirst;
        }

        n++;
    }

    // Add the item to the end of the Deque
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Invalid item to be inserted!");
        }

        Node oldLast = last;
        Node newLast = new Node();
        newLast.item = item;
        newLast.next = null;

        if (isEmpty()) {
            newLast.previous = null;
            first = last = newLast;
        } else {
            newLast.previous = oldLast;
            oldLast.next = newLast;
            last = newLast;
        }

        n++;
    }

    // Remove and return the item from the front.
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty!");
        }

        Node target = first;
        Node successor = first.next;
        // If it is not the last node in the Deque and we have a successor update
        // the previous reference to be `null`
        if (!(successor != null)) successor.previous = null;
        first = successor;

        n--;

        return target.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty!");
        }

        Node target = last;
        Node successor = last.previous;
        // If it is not the last node in the Deque and we have a successor update
        // the previous reference to be `null`
        if (!(successor != null)) successor.next = null;
        last = successor;

        n--;

        return target.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not implemented.");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more items to be returned.");
            }

            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    public static void main(String[] args) {

    }
}
