package ru.ssau.tk.ivan.lablatorn.work;

import ru.ssau.tk.ivan.lablatorn.work.exceptions.InterpolationException;
import ru.ssau.tk.ivan.lablatorn.work.function.Point;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction  {

    private Node head; //голова списка
    private int count;

    public class Node {
        double x;
        double y;
        Node next;
        Node prev;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length less than 2 point");
        }

        super.checkLengthIsTheSame(xValues, yValues);
        super.checkSorted(xValues);
        super.checkSorted(yValues);

        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        if (xFrom >= xTo || xTo < 0 | xFrom < 0) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            this.addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Incorrect index");
        }
    }

    public void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            newNode.prev = head.prev;
            newNode.next = head;
            head.prev.next = newNode;
        }
        head.prev = newNode;
        count++;
    }
    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        checkIndex(index);
        Node indexNode;
        if (index <= (count / 2)) {
            indexNode = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return indexNode;
                }
                indexNode = indexNode.next;
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return indexNode;
                }
                indexNode = indexNode.prev;
            }
        }

        return null;
    }

    @Override
    public double getX(int index) {
        checkIndex(index);
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        checkIndex(index);
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double valueY) {
        checkIndex(index);
        getNode(index).y = valueY;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    throw new IllegalArgumentException("X is less than left border");
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        if (x < leftNode.x || x > rightNode.x) {
            throw new InterpolationException();
        }
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }
}