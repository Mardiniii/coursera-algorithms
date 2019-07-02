/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 07/02/2019
 *  Description: `PointSET` is a mutable datatyp that represents a set of points
 *  in the unit square by using a red-black BST.
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private SET<Point2D> tree;

    // Initialize and empty PointSET data structure.
    public PointSET() {
        this.tree = new SET<Point2D>();
    }

    // Returns `true` or `false` if the PointSET is empty or not.
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public static void main(String[] args) {

    }
}
