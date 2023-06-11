public class SongPlaylistADT {
    Node head;
    Node tail;
    int size;

    SongPlaylistADT() {
        head = tail = null;
        size = 0;
    }

    // returns true if list is empty
    private boolean isEmpty() {
        return head == null;
    }

    // 1) public void addSong(String title, String duration)
    // Precond: title and duration as input in the
    // command prompt
    // Effect : a new node is created and added to the beginning of the doubly
    // linked list.
    // The title and duration is applied to the new node.
    // If the list is empty, a new node is created as the starting node of the list.
    public void addSong(String title, String duration) {
        // Create a new node
        Node newNode = new Node(title, duration);

        // If list is empty
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size++;
    }

    // 2) public int findSong(String t)
    // Precond : Playlist is already populated with multiple nodes using addSong()
    // Effect : the program finds the exact match of the song t and returns the
    // track number / position of the
    // song in the playlist.
    public int findSong(String t) {
        Node currentNode = head;
        int position = 1;

        while (currentNode != null) {
            if (currentNode.song.getTitle().equals(t)) {
                return position;
            }
            currentNode = currentNode.next;
            position++;
        }

        return -1; // Song not found
    }

    // 3) public void deleteAtPos(int pos)
    // Precond: Playlist is already populated with multiple nodes using addSong()
    // Effect: Deletes the song at position pos input in the command prompt. Ensure
    // the position is a valid one.
    public void deleteAtPos(int pos) {
        if (isEmpty()) {
            System.out.println("PLaylist is Empty!!!");
            return; // Empty playlist
        }

        if (pos < 1 || pos > size) {
            System.out.println("Invalid Position entered!!!");
            return;
        }

        Node current = head;
        if (pos == 1) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            }
            if (head == null) {
                tail = null;
            }
        }
        else {
            int currentPosition;
            if(pos < size/2) {
                current = head;
                currentPosition = 1;
                while (current != null && currentPosition < pos) {
                    current = current.next;
                    currentPosition++;
                }
            }
            else {
                current = tail;
                currentPosition = size;
                while (current != null && currentPosition > pos) {
                    current = current.previous;
                    currentPosition--;
                }
            }

            if (current.next != null) {
                current.next.previous = current.previous;
            }
            else {
                tail = current.previous;
            }

            if (current.previous != null) {
                current.previous.next = current.next;
            }
        }

        size--;
        System.out.println("\nSong at position " + pos + ". " + current.toString() + " deleted from the playlist.");
    }

    // 4) public String getSongAtPos(int pos)
    // Precond : Playlist is already populated with multiple nodes using addSong()
    // Effect: Returns the song title at the given position. This is used to
    // traverse the playlist (next track /
    // previous track)
    public String getSongAtPos(int pos) {
        if (isEmpty()) {
            System.out.println("Playlist is Empty !!!");
            return null;
        }

        if (pos < 1 || pos > size) {
            System.out.println("Invalid Song Position !!!");
            return null;
        }

        Node current;

        if(pos < size/2) {
            current = head;
            int currentPosition = 1;
            while (current != null && currentPosition < pos) {
                current = current.next;
                currentPosition++;
            }
        }
        else {
            current = tail;
            int currentPosition = size;
            while (current != null && currentPosition > pos) {
                current = current.previous;
                currentPosition--;
            }
        }

        return current.song.getTitle();
    }

    // 5) public void sortList()
    // Precond : Playlist is already populated with multiple nodes using addSong()
    // Effect : Sorts the songs in the playlist in alphabetical order based on the
    // song title
    public void sortList() {
        if (isEmpty() || head.next == null) {
            // List is empty or has only one node, no sorting needed
            return;
        }

        boolean swapped;
        Node current;
        Node last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.song.getTitle().compareTo(current.next.song.getTitle()) > 0) {
                    swapSongs(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }

            last = current;
        } while (swapped);
    }

    // Swapping the nodes in the doubly linked list
    private void swapSongs(Node node1, Node node2) {
        Song temp = node1.song;
        node1.song = node2.song;
        node2.song = temp;
    }

    // 6) public void display()
    // Precond: none
    // Effect : Displays the position, song title and duration of all the songs in
    // the playlist.
    public void display() {
        if (isEmpty()) {
            System.out.println("\nPlaylist is Empty !!!");
        } else {
            System.out.println("\n==============================");
            System.out.println("Playlist currently contains " + size + " song(s)");
            Node current = head;
            int position = 1;

            while (current != null) {
                System.out.println(position + ". " + current.toString());
                current = current.next;
                position++;
            }
            System.out.println("==============================\n");
        }
    }
}
