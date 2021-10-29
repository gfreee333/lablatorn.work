package ru.ssau.tk.ivan.lablatorn.work.function;

import ru.ssau.tk.ivan.lablatorn.work.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.function.factory.TabulatedFunctionFactory;
import ru.ssau.tk.ivan.lablatorn.work.operations.DifferentialOperator;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }

    class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
        public TabulatedFunctionFactory factory;

        public TabulatedDifferentialOperator() {
            this.factory = new ArrayTabulatedFunctionFactory();
        }

        public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
            this.factory = factory;
        }

        public TabulatedFunctionFactory getFactory() {
            return factory;
        }

        public void setFactory(TabulatedFunctionFactory factory) {
            this.factory = factory;
        }

        @Override
        public TabulatedFunction derive(TabulatedFunction function) {
            return null;
        }
    }
}
