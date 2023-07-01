//needed for audio
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//needed for audio file
import java.io.File;
//needed for timer
import java.util.Timer;
import java.util.TimerTask;
//needed for user input
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        //write the welcome message.
        System.out.println("Welcome to Medit8");
        //display aQ figure 8 with ASCII characters.
        System.out.println("  M______");
        System.out.println(" /E      \\");
        System.out.println("/ D       \\");
        System.out.println("\\ I       /");
        System.out.println(" \\T______/");
        System.out.println("  _______");
        System.out.println(" /       \\");
        System.out.println("/         \\");
        System.out.println("\\         /");
        System.out.println(" \\_______/");

        //Announce that the program needs user input.
        System.out.println("Please enter the amount of minutes (whole number) you would like to meditate for, or Q to exit:");
        //create a scanner object to read user input.
        Scanner scanner = new Scanner(System.in);
        //read the user input.
        String input = scanner.nextLine();
        //check if the user input is a number, or if Q then Quit.
        TestInput(input);
        }

        public static void playSound(){
            //try to load the sound file.
            try {
                //create an audio input stream object.
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound.wav").getAbsoluteFile());
                //create a clip object.
                Clip clip = AudioSystem.getClip();
                //open the audio input stream.
                clip.open(audioInputStream);
                //start the clip.
                clip.start();
            }
            //catch any exceptions.
            catch(Exception ex) {
                //write the exception to the console.
                System.out.println("Error with playing sound.");
                //print the stack trace.
                ex.printStackTrace();
            }
        }

        public static void TestInput(@org.jetbrains.annotations.NotNull String input){
            //check if the user input is a number using Regex.
            if (input.matches("[0-9]+")) {
                //convert the user input to an integer.
                int minutes = Integer.parseInt(input);
                //convert the minutes to milliseconds.
                int milliseconds = minutes * 60000;
                //inform the user of the time they have chosen.
                System.out.println("You have chosen to meditate for " + minutes + " minutes.");
                //inform the user that the timer has started.
                System.out.println("The timer has started. Please take a deep breath, close your eyes, and relax.");

                //create a timer object.
                Timer timer = new Timer();
                //create a timer task object.
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        //write the message to the console.
                        System.out.println("Time's up! Please take one last deep breath, and enjoy your day.");
                        //play the sound.
                        playSound();
                        //cancel the timer.
                        timer.cancel();
                        //allow user to press R to restart the program, or Q to quit.
                        System.out.println("Press R to restart the program, or Q to quit:");
                        //create a scanner object to read user input.
                        Scanner scanner = new Scanner(System.in);
                        //read the user input.
                        String newInput = scanner.nextLine();
                        //check if the user input is a number, or if Q then Quit.
                        TestInput(newInput);
                    }
                };
                //schedule the timer task.
                timer.schedule(task, milliseconds);

            }
            else if(input.matches("Q")){
                //write the message to the console.
                System.out.println("Goodbye!");
                //exit the program.
                System.exit(0);
            }
            else if (input.matches("R")){
                //write the message to the console.
                System.out.println("Restarting...");
                //restart the program.
                main(null);
            }
            else{ // if input is neither Q nor a whole number, then squawk and recurse.
                //write the message to the console.
                System.out.println("Please enter a whole number or Q to exit:");
                //create a scanner object to read user input.
                Scanner scanner = new Scanner(System.in);
                //read the user input.
                String newInput = scanner.nextLine();
                //check if the user input is a number, or if Q then Quit.
                TestInput(newInput);
            }
        }
    }