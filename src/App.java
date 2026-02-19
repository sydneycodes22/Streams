
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Encryption/Decryption App!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("This program provides 4 options.");
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Read a file provided by the user.");
            System.out.println("2. Encode a string to encrypted form and write it out to a file");
            System.out.println("3. Decrypt a coded string back to the original string given a file containing the encrypted data.");
            System.out.println("4. Exit the program.");

            System.out.print("Enter 1-4: ");
            String line = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-4.");
                continue;
            }
            
            CryptoModule cryptoModule = new CryptoModule();
            String filePath = "";
            
            switch (choice) {
                case 1:
                    System.out.println("Please enter the file path:");
                    filePath = scanner.nextLine().trim();
                    cryptoModule.readFromXML(filePath);
                    break;
                case 2:
                    if (filePath.isEmpty()) {
                        System.out.println("Please enter the file path:");
                        Scanner filePathScanner = new Scanner(System.in);
                        filePath = filePathScanner.nextLine().trim();
                    }
                    cryptoModule.encryptStringToFile(filePath);
                    break;
                case 3:
                    if (filePath.isEmpty()) {
                        System.out.println("Please enter the file path:");
                        Scanner filePathScanner = new Scanner(System.in);
                        filePath = filePathScanner.nextLine().trim();
                    }
                    cryptoModule.decryptStringFromFile(filePath);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
