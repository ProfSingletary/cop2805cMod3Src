// ThreadTimer.java
// join thread demo

import java.util.Scanner;

public class ThreadTimer
{
    //boolean exitFlag = false;
    public final static int NUMREPS = 5;
   
    public static void main(String[] args)
    {
        final int ARRAYSZ = 3;
        int[] numArray = new int[ARRAYSZ];
        Scanner in = new Scanner(System.in);
        Thread[] threads;
       
        // prompt and read input
        System.out.print("Please enter " + ARRAYSZ + " integers separate by a space: ");
        for (int i = 0; i < ARRAYSZ; i++)
            numArray[i] = in.nextInt();

        threads = new Thread[numArray.length];
       
        // run a thread for each number
        for (int i = 0; i < ARRAYSZ; i++)
            threads[i] = new TimerThread(numArray[i]);

        for (int i = 0; i < ARRAYSZ; i++)
        {
            try {
                threads[i].join();
                log("thread[" + i + "] joined");
            } catch (InterruptedException e) {
                log("join on thread " + i + " interrupted!");
            }
        }
       
        log("main thread is exiting!");
    }
       
    static void log(Object o)
    {
        System.out.println(o);
    }
   
    static void sleep(int msecs)
    {
        try {
            Thread.sleep(msecs);
        } catch (InterruptedException e) {
            log("interrupted");
        }   
    }
}

class TimerThread extends Thread
{
    private int timeVal; // timer in seconds
   
    public TimerThread(int timeVal)
    {
        this.timeVal = timeVal; // this is our word
        start();     // start ourselves
    }
   
    // echo the word for the specified repetitions with the specified interval
    public void run()
    {
        for (int i = 0; i < ThreadTimer.NUMREPS; i++)
        {
            ThreadTimer.log("Timer thread for value " + this.timeVal + ", iteration " + (i+1));
            ThreadTimer.sleep(this.timeVal * 1000);
        }
        ThreadTimer.log("Timer thread for value " + this.timeVal + " is exiting!");
    }
}