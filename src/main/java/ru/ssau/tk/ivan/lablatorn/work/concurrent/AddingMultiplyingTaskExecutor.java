package ru.ssau.tk.ivan.lablatorn.work.concurrent;

import ru.ssau.tk.ivan.lablatorn.work.function.ConstantFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.LinkedListTabulatedFunction;
import ru.ssau.tk.ivan.lablatorn.work.function.TabulatedFunction;

import java.util.concurrent.CountDownLatch;

public class AddingMultiplyingTaskExecutor {

    public static void main(String[] args) throws InterruptedException {

        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);
        CountDownLatch pause = new CountDownLatch(3);

        Thread thread1 = new Thread(new MultiplyingTask(function, pause::countDown));
        Thread thread2 = new Thread(new MultiplyingTask(function, pause::countDown));
        Thread thread3 = new Thread(new AddingTask(function, pause::countDown));

        thread1.start();
        thread2.start();
        thread3.start();
        pause.await();

        System.out.println(function.toString());
    }
}
