package ru.ssau.tk.ivan.lablatorn.work;

public interface TabulatedFunction {
    int getCount();
    double getX(int Index);
    double getY(int index);
    void setY(int index, double value);
    int indexOfX(double x);
    int indexOfY(double y);
    double leftBound();
    double rigthBound();

}
