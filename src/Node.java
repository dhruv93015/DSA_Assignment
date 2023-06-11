public class Node{  
    Node previous;  
    Node next;  
    Song song;
    
    public Node(String title, String duration) {  
        this.song = new Song(title, duration); 
        this.previous = null;
        this.next = null;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String toString() {
        return this.song.toString();
    }
}  