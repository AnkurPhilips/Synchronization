package com.company.hashmap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(5);

        /*
         Unsynchronized accesses between threads for shared objects will lead to erroneous results
        * */

        BankAccount bankAccount = BankAccount.getInstance();


        for(int i=0;i<5;i++){
            TransactionUnsynchronized transaction = new TransactionUnsynchronized(bankAccount);
            es.submit(transaction);

        }

        es.shutdown();

        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }


        System.out.println("Result for unsynchronized : "+BankAccount.getInstance().getAmount());


        /*
        * Synchronized method calls will give correct output but produce erroneous results for grouped statements*/



        es = Executors.newFixedThreadPool(5);

        BankAccountSynchronizedMethods bankAccountSynchronizedMethods = BankAccountSynchronizedMethods.getInstance();


        for(int i=0;i<5;i++){
            TransactionSynchronizedMethods transaction = new TransactionSynchronizedMethods(bankAccountSynchronizedMethods);
            es.submit(transaction);

        }

        es.shutdown();

        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }


        System.out.println("Result for Synchronized methods : "+BankAccountSynchronizedMethods.getInstance().getAmount());

        es = Executors.newFixedThreadPool(5);

        BankAccountSynchronizedStatements bankAccountSynchronizedStatements = BankAccountSynchronizedStatements.getInstance();


        for(int i=0;i<5;i++){
            TransactionSynchronizedStatements transaction = new TransactionSynchronizedStatements(bankAccountSynchronizedStatements);
            es.submit(transaction);

        }

        es.shutdown();

        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }


        System.out.println("Result for Synchronized statements : "+BankAccountSynchronizedStatements.getInstance().getAmount());

        /*
        * Synchronized statements will always give correct result and is suitable for atomic operations
        * */
        /*
          Result for unsynchronized : 1750
          Result for Synchronized methods : 2600
          Result for Synchronized statements : 2600
          */



    }


}
