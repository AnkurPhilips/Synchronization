package com.company.hashmap;

public class BankAccountSynchronizedStatements {

    private static BankAccountSynchronizedStatements instance=null;

    private int amount;

    private BankAccountSynchronizedStatements(int amount){
        this.amount = amount;
    }

    public static BankAccountSynchronizedStatements getInstance(){

        if(instance==null){
            synchronized (BankAccountSynchronizedStatements.class){                   // Class locked in case of static method to make lazy loading threadsafe
                if(instance==null){
                    instance = new BankAccountSynchronizedStatements(100);
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
