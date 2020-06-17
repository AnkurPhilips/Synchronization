package com.company.hashmap;

public class BankAccount {

    private static BankAccount instance=null;

    private int amount;

    private BankAccount(int amount){
        this.amount = amount;
    }

    public static BankAccount getInstance(){

        if(instance==null){
            synchronized (BankAccount.class){                   // Class locked in case of static method to make lazy loading threadsafe
                if(instance==null){
                    instance = new BankAccount(100);
                }
            }
        }
        return instance;
    }

    public void deposit(int amount){
        this.amount+=amount;
    }

    public void withdraw(int amount){
        this.amount-=amount;
    }

    public int getAmount(){
        return this.amount;
    }
}
