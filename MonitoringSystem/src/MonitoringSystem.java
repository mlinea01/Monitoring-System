
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class MonitoringSystem {
    
    //Method to create a JFrame to output the alert to the screen properly. 
    static void showAlert(String message)
        {
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(frame, message);
            frame.dispose();
        }

    public static void main(String[] args) throws IOException {
        //Create scanner to accept user input
        Scanner scnr = new Scanner(System.in);
        
        //Open both text files for use in the program
        FileInputStream Animal = new FileInputStream("D:/snhu/Animal.txt");
        FileInputStream Habitat = new FileInputStream("D:/snhu/Habitat.txt");
        
        /*
        Create a scanner object to store the information read from the 
        text file.
        Repeat this process for both text files so they will be stored in 
        memory without having to read through the files every single time the 
        program runs.  
        */
        Scanner animalFile = new Scanner(Animal);
        Animal animal = new Animal();
        List <Animal> zooAnimals = new ArrayList <>();
        
        Scanner habitatFile = new Scanner(Habitat);
        Habitat habitat = new Habitat();
        List <Habitat> animalHabitats = new ArrayList <>();
        
        /* 
        While there is text on the next line of the text file, the loop will go
        through the text file and look for key terms like animal, name, etc., 
        and store the correct line of text in the appropriate variable and those 
        variables are stored in an animal object created from the Animal class.
        Once all the variables have the correct values and are stored in the
        animal obect, the animal object is then added to the zooAnimals list 
        for use later in the program.  This process continues until every 
        animal, and their corresponding values, each have their own place in 
        the list.  So for animals, there would be 4 list items created and 
        stored in the list zooAnimals.
        
        This same process is applied to the habitats if the user 
        would like to monitor a habitat instead of animal.
        */
        
        String Line;
        
        while(animalFile.hasNext())
        {
            Line = animalFile.nextLine();
                
            if(Line.contains("Animal"))
            {
                animal.animal = Line;
            }
            else if(Line.contains("Name"))
            {
                animal.animalName = Line;
            }
            else if(Line.contains("Age"))
            {
                animal.age = Line;
            }
            else if(Line.contains("Health concerns"))
            {
                animal.healthConcerns = Line;
            }
            else if(Line.contains("Feeding schedule"))
            {
                animal.feedingSched = Line;
                zooAnimals.add(animal);
                animal = new Animal();
            }
        }
        
        while(habitatFile.hasNext())
        {
            Line = habitatFile.nextLine();
            
            if(Line.contains("Habitat"))
            {
                habitat.habitat = Line;
            }
            else if(Line.contains("Temperature"))
            {
                habitat.temperature = Line;
            }
            else if(Line.contains("Food source"))
            {
                habitat.foodSource = Line;
            }
            else if(Line.contains("Cleanliness"))
            {
                habitat.cleanliness = Line;
                animalHabitats.add(habitat);
                habitat = new Habitat();
            }
        }
        
        //input variables created to store user input. 
        int input = 0;
        int input2 = 0;
        
        /*
        Prompt user to enter whether he/she would like to monitor an
        an animal, habitat, or exit the program.
        */ 
        System.out.println("Would you like to monitor an animal, habitat,"
                    + " or exit the program?");
        
        System.out.println("1. Animal");
        System.out.println("2. Habitat");
        System.out.println("3. Exit");
        input = scnr.nextInt();
        System.out.println("");
        
        /*
        After the user specifices whether he/she would like to monitor an 
        animal or habitat, the program enters this while loop.  . 
        */
        while (input != 3)
        {
            //Created a string variable to store alert messages.
            String alert;
            
            /*
            if input is equal to 1, the program will ask the user to enter
            which animal he/she would like to monitor.  The program then 
            outputs the correct information for that animal.  
            */
            if (input == 1)
            {
                System.out.println("Which animal would you like to monitor?");
                
                /*
                Outputs the options the user has to choose from, from the 
                text file.
                */
                for (int i = 0; i < zooAnimals.size(); i++ )
                {
                    String animalName = zooAnimals.get(i).animal.substring(9);
                    System.out.println((i+1) + ". " + animalName);
                } 
                
                //Variable inilialized to allow user to input second option.
                input2 = scnr.nextInt();
                System.out.println("");
                
                /*
                if statement to eliminate out of bounds error if user enters
                a number larger than 4 or less than 1.  4 is the last number
                in this list. 
                */
                if(input2 <= 4 && input2 > 0)
                {
                    /*
                    After the user chooses an option he/she wants, the 
                    if-elseif statements check to see if there are any alerts
                    for health concerns or feeding schedule.  If there are, an
                    alert box is output to the screen and after that box is 
                    terminated the rest of the information is output to the 
                    screen as seen in the text file.
                    If there is no alert, the information will be output to the 
                    screen normally, as seen in the text file.  
                    */
                    if(zooAnimals.get(input2-1).healthConcerns.contains("*"))
                    {
                        alert = zooAnimals.get(input2-1).healthConcerns.substring(5);
                        showAlert(alert);
                    }
                    else if(zooAnimals.get(input2-1).feedingSched.contains("*"))
                    {
                        alert = (zooAnimals.get(input2-1).feedingSched.substring(5));
                        showAlert(alert);
                    }
                    System.out.println(zooAnimals.get(input2-1).animal);
                    System.out.println(zooAnimals.get(input2-1).animalName);
                    System.out.println(zooAnimals.get(input2-1).age);
                    System.out.println(zooAnimals.get(input2-1).healthConcerns);
                    System.out.println(zooAnimals.get(input2-1).feedingSched);
                    System.out.println("");
                }
                else
                {
                    System.out.println("You must enter a number from the list");
                    System.out.println("");
                    continue;
                }
                
            }
            
            /*
            If input is equal to 2, the program will ask the user to enter
            which habitat he/she would like to monitor.  The program then
            outputs the correct information for that habitat. 
            */
            else if (input == 2)
            {
                System.out.println("Which habitat would you like to monitor?");
                
                /*
                Outputs the options the user has to choose from, from the 
                text file.
                */
                for (int i = 0; i < animalHabitats.size(); i++)
                {
                    String habName = animalHabitats.get(i).habitat.substring(9);
                    System.out.println((i+1) + ". " + habName);
                }
                
                //Variable initialized to allow user to input second option.
                input2 = scnr.nextInt();
                System.out.println("");
                
                /*
                if statement to eliminate out of bounds error if user enters
                a number larger than 3 or less than 1.  3 is the last number
                in this list. 
                */
                if (input2 <= 3 && input2 > 0)
                {
                    /*
                    After the user chooses an option he/she wants, the 
                    if-elseif statements check to see if there are any alerts
                    for food source or cleanliness.  If there are, an
                    alert box is output to the screen and after that box is 
                    terminated the rest of the information is output to the 
                    screen as seen in the text file.
                    If there is no alert, the information will be output to the 
                    screen normally, as seen in the text file.  
                    */
                    if(animalHabitats.get(input2-1).foodSource.contains("*"))
                    {
                        alert = animalHabitats.get(input2-1).foodSource.substring(5);
                        showAlert(alert);
                    }
                    else if(animalHabitats.get(input2-1).cleanliness.contains("*"))
                    {
                        alert = animalHabitats.get(input2-1).cleanliness.substring(5);
                        showAlert(alert);
                    }
                    System.out.println(animalHabitats.get(input2-1).habitat);
                    System.out.println(animalHabitats.get(input2-1).temperature);
                    System.out.println(animalHabitats.get(input2-1).foodSource);
                    System.out.println(animalHabitats.get(input2-1).cleanliness);
                    System.out.println("");
                }
                else
                {
                    System.out.println("You must enter a number from the list.");
                    System.out.println("");
                    continue;
                }
            }
         
            
            System.out.println("Would you like to monitor an animal, habitat,"
                    + " or exit the program?");
        
            System.out.println("1. Animal");
            System.out.println("2. Habitat");
            System.out.println("3. Exit");
            input = scnr.nextInt();
            System.out.println("");
        }
    }
    
}
