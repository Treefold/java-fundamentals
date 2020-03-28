public class Stack {
    private int maxSize;
    private int currentSize = 0;
    private Task[] stack;

    public Stack () {
        maxSize = 10;
        stack   = new Task[maxSize];
    }

    public Stack (int size) {
        if (size < 1) {
            size = 10;
        }
        maxSize = size;
        stack   = new Task[maxSize];
    }

    public Stack (Stack s) {
        maxSize     = s.maxSize;
        currentSize = s.currentSize;
        stack       = s.stack.clone();
    }

    public void free () {
        currentSize = 0;
    }

    public void reInit (int size) {
        if (size < 1) {
            size = 10;
        }
        free();
        stack = new Task[size];
    }

    public boolean isEmpty () {
        return currentSize == 0;
    }

    public boolean isFull () {
        return currentSize >= maxSize;
    }

    public boolean add (Task t) {
        if (isFull()) {
            return false;
        }
        stack[currentSize++] = t;
        return true;
    }

    public void perform () {
        if (!isEmpty()) {
            stack[--currentSize].DoYourJob();
        }
    }

    public void abandon () {
        if (!isEmpty()) {
            --currentSize;
        }
    }
}
