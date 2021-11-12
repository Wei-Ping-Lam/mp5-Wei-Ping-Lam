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
        boolean flag;
        try {
            queue.put(item);
            flag = true;
        }catch (InterruptedException e){
            flag = false;
        }
        return flag;
    }

    public T take(){
        T flag;
        try {
            flag = queue.take();
        }catch (InterruptedException e){
            flag = null;
        }
        return flag;
    }

}
