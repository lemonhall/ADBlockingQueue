import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by lemonhall on 15/7/10.
 */
public class HelloAdq {

    private static ADBlockingQueue adq = new ADBlockingQueue(100000);

    public static class getData implements Runnable{

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p/>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            while (true) {
                try {
                    Object o = adq.take();
                    System.out.println(o);
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String... args) throws InterruptedException {

        getData gworker = new getData();
        Thread t1 = new Thread(gworker);
        t1.start();

        sleep(2000);
        Random r = new Random();
        while (true){
                adq.put(r.nextInt(999));
                //sleep(r.nextInt(10));
        }
    }
}
