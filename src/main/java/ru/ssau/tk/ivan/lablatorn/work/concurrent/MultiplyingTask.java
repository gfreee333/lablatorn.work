package ru.ssau.tk.ivan.lablatorn.work.concurrent;

import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction function;
    private final Runnable postRunAction;

    public MultiplyingTask(TabulatedFunction function, Runnable postRunAction) {
        this.function = function;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < function.getCount(); i++) {
            x = function.getX(i);
            synchronized (function) {
                y = function.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f", Thread.currentThread().getName(), i, x, y);
                System.out.println();
                function.setY(i, y * 10);
                y = function.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f", Thread.currentThread().getName(), i, x, y);
            System.out.println();

        }
        if (postRunAction != null) {
            postRunAction.run();
        }
    }
}
