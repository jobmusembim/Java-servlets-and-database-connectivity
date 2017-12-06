
import java.sql.Connection;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class DBConnector {
  public  static Scanner scanner = new Scanner(System.in);
        public  static char choice;
        public static int i, student_no;
        public  static boolean doneAdding;
        public  static String first_name, last_name, programme, gender;  
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection con = new Demo().connector();
        
//        System.out.println("Please select what you want to do. Reply with");
//        System.out.println("A: To add a new record");
//        System.out.println("D: To delete/remove a record");
//        System.out.println("U: To update a record");
//        String reply;
//        Scanner scanner = new Scanner(System.in);
//        reply = scanner.nextLine();
//        switch (reply){
//            case "A":
//               int student_no;
//               String first_name, last_name, gender, programme;
//                System.out.println("Your student number");
//                student_no = scanner.nextInt();
//                System.out.println("Your f name");
//                first_name = scanner.nextLine();
//                System.out.println("Your l name");
//                last_name = scanner.nextLine();
//                System.out.println("Your gender");
//                gender = scanner.nextLine();
//                System.out.println("Your programme");
//                programme = scanner.nextLine();
//                CrudOperations crudOperations = new CrudOperations();
//                crudOperations.setStudent_no(student_no);
//                crudOperations.setFirst_name(first_name);
//                crudOperations.setLast_name(last_name);
//                crudOperations.setGender(gender);
//                crudOperations.setProgramme(programme);
//               break;
//            case "D":
//                
//                break;
//            case "U":
//                
//                break;
//            default:
//                System.out.println("Invalid Entry");
//      }
    }
     static void getDets() {

        System.out.println("Please enter student's Number: ");
        student_no = scanner.nextInt();
                        
        System.out.println("Please enter student's First Name: ");
        first_name =  scanner.next();
                        
        System.out.println("Please enter student's Last Name: ");
        last_name =  scanner.next();
                        
        System.out.println("Please enter student's Degree Programme: ");
        programme =  scanner.next();
                    
        System.out.println("Please select student's gender :('M'/'F')");
        gender = scanner.next(); 
                        
        CrudOperations cop = new CrudOperations();
            cop.setStudent_no(student_no);
            cop.setFirst_name(first_name);
            cop.setLast_name(last_name);
            cop.setProgramme(programme);
            cop.setGender(gender);
            cop.save();
            
        
        // Loop to get more students to register, while saving them.            
        System.out.println("Are you done registering students?\n ('Y'/'N') ");
        choice = scanner.next().trim().charAt(0);
        
            switch (choice){
    
                case 'Y':
                    System.out.println("You chose to finish adding new students");
                    System.exit(0);
                    break;
                case 'N':
                    System.out.println("You chose to continue adding new students");
                    getDets();
                    break;
                default:
                    System.out.print("Invalid  option.\n");
                    break;
                }
     }
}
