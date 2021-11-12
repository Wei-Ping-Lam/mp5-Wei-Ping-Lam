package Terminals;

import Entities.Message;
import Entities.Queue;

public class MessageQueue extends Queue<Message> {

    private final static int messageQueueSize = 10;
    MessageQueue(){
        super(messageQueueSize);
    }



}
