package ru.ssau.tk.ivan.lablatorn.work.operations;

import ru.ssau.tk.ivan.lablatorn.work.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
