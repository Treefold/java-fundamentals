public class Queue {
    private int maxSize;
    private int st  = 0;
    private int fin = 0;
    private Task[] queue;

    public Queue () {
        maxSize = 10;
        queue   = new Task[maxSize];
    }

    public Queue (int size) {
        if (size < 1) {
            size = 10;
        }
        maxSize = size;
        queue   = new Task[maxSize];
    }

    public Queue (Queue q) {
        maxSize = q.maxSize;
        st      = q.st;
        fin     = q.fin;
        queue   = q.queue.clone();
    }

    public void free () {
        st = fin = 0;
    }

    public void reInit (int size) {
        if (size < 1) {
            size = 10;
        }
        free();
        queue = new Task[size];
    }

    public boolean isEmpty () {
        return st == fin;
    }

    public boolean isFull () {
        return (fin + 1) % maxSize == st;
    }

    public boolean add (Task t) {
        if (isFull()) {
            return false;
        }
        queue[fin] = t;
        fin = (fin + 1) % maxSize;
        return true;
    }

    public void perform () {
        if (!isEmpty()) {
            queue[st].DoYourJob();
            st = (st + 1) % maxSize;
        }
    }

    public void abandon () {
        if (!isEmpty()) {
            st = (st + 1) % maxSize;
        }
    }
}
