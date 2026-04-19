package com.shivesh.ai.java;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestClass {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> future =null;
        long startTime = System.currentTimeMillis();
        for(int i=1; i < 99; i++){
            int finalI = i;
            future = executorService.submit(() -> {
                System.out.println(factorial(finalI));
            });

        }
        long timeElapsed = System.currentTimeMillis() - startTime;
        System.out.println(future.get());
        if(future.isDone()) {
            System.out.println("Time Elapsed: " + timeElapsed);
        }
        executorService.shutdown();

    }

    private static long factorial(int n){
        //return i * factorial(i-1);
        long result =1;
        for(int i=n ; i>1; i--){
            result *= i;
        }
        return result;
    }
}
