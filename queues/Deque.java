/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/04/2019
 *  Description: Deque is a generalization of a stack and a queue that supports
 *  adding and removing items from either the front or the back of the data
 *  structure.
 **************************************************************************** */

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
        return n == 0;
    }

    // Returns the number of items in the Deque.
    public int size() {
        return n;
    }

    // Add the item to front of the Deque.
    public void addFirst(Item item) {
        Node oldFirst = first;
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.previous = null;

        if (isEmpty()) {
            newFirst.next = null;
            first = newFirst;
            last = first;
        } else {
            newFirst.next = oldFirst;
        }
    }

    public static void main(String[] args) {

    }
}
