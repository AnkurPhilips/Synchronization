package com.company.hashmap;

public class BankAccountSynchronizedMethods {

    private static BankAccountSynchronizedMethods instance=null;

    private int amount;

    private BankAccountSynchronizedMethods(int amount){
        this.amount = amount;
    }

    public static BankAccountSynchronizedMethods getInstance(){

        if(instance==null){
            synchronized (BankAccountSynchronizedMethods.class){                   // Class locked in case of static method to make lazy loading threadsafe
                if(instance==null){
                    instance = new BankAccountSynchronizedMethods(100);
                }
            }
        }
        return instance;
    }


    public synchronized void deposit(int amount){
        this.amount+=amount;
    }

    public synchronized void withdraw(int amount){
        this.amount-=amount;
    }

    public synchronized int getAmount(){
        return this.amount;
    }
}
