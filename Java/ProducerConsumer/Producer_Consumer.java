import java.util.LinkedList;

class Producer_Consumer 
{
    LinkedList<Integer> list = new LinkedList<>(); 
    int capacity = 5; 

    //Producer Function
    public void produce() throws InterruptedException 
    { 
        int value = 0; 
        while (true) 
        { 
        	//Creates an atomic block
            synchronized (this) 
            { 
                //Thread waits if the buffer is full
                while (list.size()==capacity) 
                    wait(); 

                System.out.println("Producer produced-"
                                              + value); 

                //adds the thread to the list
                list.add(value++); 

                //Notifies the consumer that it can start consuming
                notify(); 

                Thread.sleep(2000); 
            } 
        } 
    } 

    //Consumer Function
    public void consume() throws InterruptedException 
    { 
        while (true) 
        { 
        	//Creates an atomic block
            synchronized (this) 
            { 
                //Thread waits if all the elements of the buffer is consumed
                while (list.size()==0) 
                    wait(); 
                
                //Consumes the element from the beginning
                int val = list.removeFirst(); 

                System.out.println("Consumer consumed-"
                                                + val); 

                //Wake up producer thread 
                notify(); 

                Thread.sleep(2000); 
            } 
        } 
    } 
} 

class Execute_PC_Thread 
{
	public static void main(String[] args) throws InterruptedException 
	{ 
		final Producer_Consumer pc = new Producer_Consumer(); 
		
		//Create producer thread 
		Thread t1 = new Thread(new Runnable() 
		{ 
			@Override
			public void run() 
			{ 
			    try
			    { 
			        pc.produce(); 
			    } 
			    catch(InterruptedException e) 
			    { 
			        e.printStackTrace(); 
			    } 
			} 
		}); 
	
		//Create consumer thread 
		Thread t2 = new Thread(new Runnable() 
		{ 
			@Override
			public void run() 
			{ 
			    try
			    { 
			        pc.consume(); 
			    } 
			    catch(InterruptedException e) 
			    { 
			        e.printStackTrace(); 
			    } 
			} 
		}); 
		
		//Start both threads 
		t1.start(); 
		t2.start(); 
	}
}
