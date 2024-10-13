class DataHolder {  
    private int[] data;  
    private int index;  
  
    public DataHolder() {  
        data = new int[10];  
        index = 0;  
    }  
  
    public void setValue(int value) {  
        if (index < 9) {  
            System.out.println("Setting value " + value + " in the data array");  
            data[index] = value;  
            index++;  
        }  
    }  
  
    public int getValue() {  
        if (index > 0) {  
            index--;  
            System.out.println("Returning value: " + data[index]);  
            return data[index];  
        } else {  
            return -1;  
        }  
    }  
}  
  
class MyThread extends Thread {  
    private DataHolder dataHolder;  
  
    public MyThread(String threadName, DataHolder dataHolder) {  
        super(threadName);  
        this.dataHolder = dataHolder;  
    }  
  
    public void run() {  
        synchronized (dataHolder) {  
            int value = 0;  
            while (value < 5) {  
                System.out.println("Thread Name: " + Thread.currentThread().getName());  
                value++;  
                dataHolder.setValue(value);  
                try {  
                    Thread.sleep(100);  
                    System.out.println(Thread.currentThread().getName() + " is awake now");  
                } catch (InterruptedException e) {  
                    System.out.println("Exception caught");  
                }  
                if (value == 2) {  
                    try {  
                        System.out.println(Thread.currentThread().getName() + " is suspending");  
                        suspend();  
                    } catch (SecurityException e) {  
                        System.out.println("Exception caught while suspending thread");  
                    }  
                }  
            }  
        }  
    }  
}  
  
public class DeadlockUsingDeprecatedMethods {  
    public static void main(String[] args) {  
        // Create a shared DataHolder object  
        DataHolder dataHolder = new DataHolder();  
  
        // Create two threads that operate on the same DataHolder object  
        MyThread thread1 = new MyThread("Thread A", dataHolder);  
        MyThread thread2 = new MyThread("Thread B", dataHolder);  
  
        // Start the threads  
        thread1.start();  
        thread2.start();  
  
        // Main thread performs its own operations  
        for (int i = 500; i <= 501; i++) {  
            System.out.println("Main Thread " + i);  
        }  
    }  
}  
