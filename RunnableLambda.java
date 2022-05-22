// RunnableLambda.java
// Create 2 threads using the legacy approach
// (implement run() as regular method)
// and then create 2 threads using lambdas.

public class RunnableLambda
{
    public static final int NUM_TIMES = 25;
    
    public static void main(String[] args) {
        Runnable pc1 = new PrintCharV2('a');
        Runnable pc2 = new PrintCharV2('b');
        Thread t1 = new Thread(pc1);
        Thread t2 = new Thread(pc2);
        Runnable r; // use this for our lambdas

        // Technically a for loop is a single statement, but the
        // compiler requires surrounding curly braces for a lambda
        // don’t forget final semi-colon !!!
        r  = () -> { 
            for (int i = 0; i < NUM_TIMES; i++) System.out.print('c'); 
        };
        Thread t3 = new Thread(r);

         r  = () -> { 
             for (int i = 0; i < NUM_TIMES; i++) System.out.print('d'); 
         };
         Thread t4 = new Thread(r);

         t1.start();
         t2.start();
         t3.start();
         t4.start();
    }
}

class PrintCharV2 implements Runnable {
    private char charToPrint;
    
    PrintCharV2(char c) {
        charToPrint = c;
    }
    
    public void run() {
        for (int i = 0; i < RunnableLambda.NUM_TIMES; i++) {
            System.out.print(charToPrint);
        }
    }
}