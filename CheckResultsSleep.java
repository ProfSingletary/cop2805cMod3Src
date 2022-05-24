public class CheckResultsSleep {
   private static int counter = 0;
   public static void main(String[] args){

      new Thread(() -> {
         for(int i=0; i < 500; i++)
             CheckResultsSleep.counter++;
      }).start();

      while(CheckResultsSleep.counter < 100) {
         System.out.println("Not reached yet");
         try {
         Thread.sleep(1000); // 1 SECOND
         } catch (InterruptedException ie) { 
             System.out.println("interrupted!");
         }
      }
      System.out.println("Reached!");
   }
}
