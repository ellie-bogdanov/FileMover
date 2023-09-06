
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*System.out.println("enter path for files to move: ");
        String filesToMove = scanner.nextLine();
        System.out.println("enter new destination for files" );
        String newDestination = scanner.nextLine();*/

        String A2FilesToMove = Constants.A2Path;
        String A3FilesToMove = Constants.A3Path;
        System.out.println("type next to get to the next file");
        String  nextFile = scanner.nextLine();
        
        File A2Directory = new File(A2FilesToMove);
        File A3Directory = new File(A3FilesToMove);
        
        while(nextFile.equals("next")) {
            
            File [] A2files = A2Directory.listFiles(File::isFile);
            File [] A3files = A3Directory.listFiles(File::isFile);

            long A2LastModifiedTime = Long.MIN_VALUE;
            long A3LastModifiedTime = Long.MIN_VALUE;

            File A2ChosenFile = null;
            File A3ChosenFile = null;

            System.out.println("grab A2 file? type y for yes");
            String grabA2File = scanner.nextLine();
            if(grabA2File.equals("y")){
                if(A2files != null) {
                    for (File file : A2files) {
                        if(file.lastModified() > A2LastModifiedTime) {
                            A2ChosenFile = file;
                            A2LastModifiedTime = file.lastModified();
                        }
                    }
                }
            }

            System.out.println("grab A3 file? type y for yes");
            String grabA3File = scanner.nextLine();

            if(grabA3File.equals("y")) {
                if(A3files != null) {
                    for (File file : A3files) {
                        if(file.lastModified() > A3LastModifiedTime) {
                            A3ChosenFile = file;
                            A3LastModifiedTime = file.lastModified();
                        }
                    }
                }
            }


            System.out.println("Enter Client Number: ");
            String clientNumber = scanner.nextLine();
            System.out.println("Enter Project Number: ");
            String projectNumber = scanner.nextLine();
            File dir = new File(Constants.newDestinationPath + "\\" + clientNumber + "\\" + projectNumber);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            if(grabA2File.equals("y")) {
                File destination = new File(Constants.newDestinationPath + "\\" + clientNumber + "\\" + projectNumber + "\\" + A2ChosenFile.getName());
                if (!destination.exists()) {
                    A2ChosenFile.renameTo(destination);
                }
            }

            if(grabA3File.equals("y")) {
                File destination = new File(Constants.newDestinationPath + "\\" + clientNumber + "\\" + projectNumber + "\\" + A3ChosenFile.getName());
                if (!destination.exists()) {
                    A3ChosenFile.renameTo(destination);
                }
            }

            

            System.out.println("type next to get to the next file or anything else to stop");
            nextFile = scanner.nextLine();
        }
        scanner.close();
    }
}