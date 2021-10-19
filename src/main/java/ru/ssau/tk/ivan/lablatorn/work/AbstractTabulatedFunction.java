package ru.ssau.tk.ivan.lablatorn.work;

import ru.ssau.tk.ivan.lablatorn.work.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.ivan.lablatorn.work.exceptions.DifferentLengthOfArraysException;

import java.util.Iterator;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    public void checkLengthIsTheSame(double[] xValues, double[] yValues) throws DifferentLengthOfArraysException {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }

    public void checkSorted(double[] array) throws ArrayIsNotSortedException {
        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

}
