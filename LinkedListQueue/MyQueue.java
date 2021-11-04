package LinkedListQueue;

public class MyQueue<T> implements QueueADT {
    MyLinkedList<T> queue;
    public MyQueue() {
        this.queue = new MyLinkedList<T>();
    }
    public MyQueue(T... vals) {
        this.queue = new MyLinkedList<>(vals);
    }
    @Override
    public void offer(Object item) { this.queue.add((T)item); }
    @Override
    public Object poll() { return this.queue.remove(0); }
    @Override
    public Object peek() { return this.queue.get(0); }
    @Override
    public int size() { return this.queue.size(); }
    public boolean isEmpty() { return this.queue.isEmpty(); }
    @Override
    public void clear() { this.queue = new MyLinkedList<>(); }

}
