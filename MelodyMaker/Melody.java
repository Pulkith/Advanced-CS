package MelodyMaker;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {
    Queue<Note> notes;
    Melody(Queue<Note> song) { this.notes = song; }
    double getTotalDuration() {
        double ans = 0;
        Queue<Note> temp = new LinkedList<>();
        boolean repeat = false;
        while(this.notes.size() > 0) {
            if(notes.peek().isRepeat()) repeat = !repeat;
            if(repeat || notes.peek().isRepeat()) ans += this.notes.peek().getDuration();
            ans += this.notes.peek().getDuration();
            temp.offer(notes.poll());
        }
        while(temp.size() > 0) notes.offer(temp.poll());
        return ans;
    }
    public String toString() {
        String ret = "";
        Queue<Note> temp = new LinkedList<>();
        while(this.notes.size() > 0) {
            ret += this.notes.peek() + "\n";
            temp.offer(notes.poll());
        }
        while(temp.size() > 0) this.notes.offer(temp.poll());
        return ret;
    }
    void changeTempo(double tempo) {
        Queue<Note> temp = new LinkedList<>();
        while(this.notes.size() > 0) {
            this.notes.peek().setDuration(this.notes.peek().getDuration() / tempo);
            temp.offer(notes.poll());
        }
        while(temp.size() > 0) this.notes.offer(temp.poll());

    }
    void reverse() {
        Stack<Note> rev = new Stack<>();
        while(notes.size() > 0) rev.push(notes.poll());
        while(rev.size() > 0) notes.offer(rev.pop());
    }
    void append(Melody other) {
        Queue<Note> temp = new LinkedList<>();
        while(other.notes.size() > 0) temp.offer(other.notes.poll());
        while(temp.size() > 0) {
            other.notes.offer(temp.peek());
            this.notes.offer(temp.poll());
        }
    }
    void play() {
        Queue<Note> repeat = new LinkedList<>(), temp = new LinkedList<>();
        while(this.notes.size() > 0) {
            this.notes.peek().play();
            if(this.notes.peek().isRepeat()) {
                repeat.offer(notes.peek());
                if(repeat.size() > 1) {
                    while(repeat.size() > 0)
                        repeat.poll().play();;
                }
            }
            temp.offer(notes.poll());
        }
        while(temp.size() > 0) this.notes.offer(temp.poll());
    }


    Queue<Note> getNotes() {
        return notes;
    }
}
