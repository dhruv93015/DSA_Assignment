import java.util.Scanner;

public class SongPlaylist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SongPlaylistADT playlist = new SongPlaylistADT();

        int option = 0;
        do {
            System.out.println("\nPlaylist Operations:\n");
            System.out.println("1. Add a song to the playlist");
            System.out.println("2. Delete a song from the playlist");
            System.out.println("3. Find a song by name");
            System.out.println("4. Next track / Previous track");
            System.out.println("5. Sort playlist by song title");
            System.out.println("6. Display playlist");
            System.out.println("7. Exit");

            try {
                System.out.print("\nEnter Menu Option: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        System.out.print("\nEnter Song Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Song Duration: ");
                        String duration = scanner.nextLine();
                        if(title.strip() == "") {
                            System.out.println("\nSong title can not be empty !!!");
                        }
                        else {
                            playlist.addSong(title, duration);
                            System.out.println("\nAdded the song '" + title + "' to the playlist");
                        }
                        break;
                    case 2:
                        System.out.print("\nEnter the position of the song to delete: ");
                        int position = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        playlist.deleteAtPos(position);
                        break;
                    case 3:
                        System.out.print("\nEnter the name of the song: ");
                        String songName = scanner.nextLine();
                        int songPosition = playlist.findSong(songName);
                        if (songPosition != -1) {
                            System.out.println("The Song '" + songName + "' is present at position " + songPosition);
                        } else {
                            System.out.println("The Song '" + songName + "' not found in the playlist");
                        }
                        break;
                    case 4:
                        System.out.print("\nEnter a track number to start from: ");
                        int startTrack = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        char direction;
                        do {
                            String currentSongTitle = playlist.getSongAtPos(startTrack);
                            if(currentSongTitle == null) {
                                break;
                            }

                            System.out.println(
                                    "\nCurrent Song is " + startTrack + ". " + currentSongTitle);
                            System.out.println("\n\tEnter:\n\t'n' for Next Track\n\t'p' for Previous Track\n\t'e' to exit");
                            direction = scanner.nextLine().charAt(0);
                            if (direction == 'n') {
                                startTrack++;
                                if (startTrack > playlist.size) {
                                    System.out.println("End of playlist reached");
                                    startTrack = playlist.size;
                                }
                            } else if (direction == 'p') {
                                startTrack--;
                                if (startTrack < 1) {
                                    System.out.println("Beginning of playlist reached");
                                    startTrack = 1;
                                }
                            }
                        } while (direction != 'e');
                        break;
                    case 5:
                        playlist.sortList();
                        System.out.println("\nPlaylist sorted by song title\n");
                        playlist.display();
                        break;
                    case 6:
                        playlist.display();
                        break;
                    case 7:
                        System.out.println("\nExiting the program");
                        break;
                    default:
                        System.out.println("\nInvalid option! Please try again.");
                        break;
                }
            } catch (Exception e) {
                //System.out.println(e);
                System.out.println("Invalid input entered !!!");
                scanner.nextLine(); // Clean the buffer
            }
        } while (option != 7);

        scanner.close();
    }
}

// END Of the PROGRAM