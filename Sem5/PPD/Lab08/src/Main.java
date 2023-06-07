import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        BankAccount2 first = new BankAccount2(200), second = new BankAccount2(3000);

        Thread t1 = new Thread(() -> first.withdraw(10));
        Thread t2 = new Thread(() -> first.deposit(10));
        Thread t3 = new Thread(() -> first.transfer(second, 10));
        Thread t4 = new Thread(() -> second.withdraw(310));
        Thread t5 = new Thread(() -> second.transfer(first, 120));

        System.out.println("First: " + first.getAmmount());
        System.out.println("Second: " + second.getAmmount());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
//        t6.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
//            t6.join();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("\nFirst: " + first.getAmmount());
        System.out.println("Second: " + second.getAmmount());
    }
}

class BankAccount {

    private double ammount;
    private final Lock lock = new ReentrantLock();

    public BankAccount(double ammount) {
        this.ammount = ammount;
    }

    public double getAmmount() {
        return ammount;
    }

    public boolean withdraw(double sum) {
        lock.lock();

        if (ammount >= sum) {
            ammount -= sum;

            lock.unlock();
            return true;
        }

        lock.unlock();
        return false;
    }

    public void deposit(double sum) {
        lock.lock();
        ammount += sum;
        lock.unlock();
    }

    public boolean transfer(BankAccount other, double sum) {
        lock.lock();
        other.lock.lock();

        if (sum <= ammount) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            ammount -= sum;
            other.ammount += sum;

            lock.unlock();
            other.lock.unlock();
            return true;
        }

        lock.unlock();
        other.lock.unlock();
        return false;
    }
}

class BankAccount2 {

    private double ammount;

    public BankAccount2(double ammount) {
        this.ammount = ammount;
    }

    public synchronized double getAmmount() {
        return ammount;
    }

    public synchronized boolean withdraw(double sum) {

        if (ammount >= sum) {
            ammount -= sum;

            return true;
        }

        return false;
    }

    public synchronized void deposit(double sum) {

        ammount += sum;
    }

    public boolean transfer(BankAccount2 other, double sum) {

        synchronized (this) {
            synchronized (other) {
                if (sum <= ammount) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    ammount -= sum;
                    other.ammount += sum;

                    return true;
                }

                return false;
            }
        }
    }
}