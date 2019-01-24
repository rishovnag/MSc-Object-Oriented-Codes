import java.util.concurrent.Semaphore;

class ReaderWritersProblem {

    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);
    static int readCount = 0;
    static int val=0;
    static class Read implements Runnable {
        @Override
        public synchronized void run() {
            try {
                //readLock.acquire();
                readCount++;
                if (readCount >= 1) {
                    writeLock.acquire();
                }
                System.out.println("Thread "+val+" is READING");
                Thread.sleep(1500);
                System.out.println("Thread "+val+" has finished READING");
                readLock.release();
                val++;
                readCount--;
                if(readCount == 0) {
                    writeLock.release();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class Write implements Runnable {
        @Override
        public synchronized void run() {
            try {
                writeLock.acquire();
                System.out.println("Thread "+val+" is WRITING");
                Thread.sleep(2500);
                System.out.println("Thread "+val+" has finished WRITING");
                writeLock.release();
                val++;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    

    public static void main(String[] args) throws Exception {
        Read read = new Read();
        Write write = new Write();
        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(write);
        t2.setName("thread2");
        while(true)
        {
        	t1.run();
        	t2.run();
        }
    }
}