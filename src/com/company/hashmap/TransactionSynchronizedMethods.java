package com.company.hashmap;

public class TransactionSynchronizedMethods implements Runnable {
    private BankAccountSynchronizedMethods account;

    public TransactionSynchronizedMethods(BankAccountSynchronizedMethods account) {
        this.account = account;
    }

    @Override
    public void run() {


        for(int i=0;i<10;i++) {

            try {
                Thread.sleep(500);
            }
            catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
            }



            account.deposit(100);

            try {
                Thread.sleep(500);
            }
            catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
            }

            account.withdraw(50);
        }
    }
}
