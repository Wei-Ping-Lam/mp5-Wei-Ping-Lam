package Entities;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public abstract class Queue<T> {

    BlockingQueue<T> queue;

    public Queue(int size)
    {

        queue = new ArrayBlockingQueue<T>(size);
    }

    public boolean put(T item){
        try {
            queue.put(item);
            return true;
        }catch (InterruptedException e){
            return false;
        }
    }

    public T take(){
        try {
            return queue.take();
        }catch (InterruptedException e){
            return null;
        }
    }

}
