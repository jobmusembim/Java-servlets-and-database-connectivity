
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author job
 */
public class CrudOperations {
    private int student_no;
    private String first_name;
    private String last_name;
    private String gender;
    private String programme;
    private String pass;
//setter
    public void setStudent_no(int student_no) {
        this.student_no = student_no;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProgramme(String programe) {
        this.programme = programe;
    }
    public void setPass(String pass) {
    this.pass = pass;
    }
//getter
    public int getStudent_no() {
        return student_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getProgramme() {
        return programme;
    }
    public String getPass() {
        return pass;
    }
    //methods
    public int processGender(String g){
        if(g.equalsIgnoreCase("male")){
            return 1;
        }
        return 0;
    }
    
       public String processGender(int g){
        if ( g == 1 ){
            return "Male";
        }return "Female";
        
    }
    
        //method to verify users on login
    public boolean checkRecord(int reg_number) {
        ResultSet rs = null; 
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
    // Check that the record exists
        try{
        
        pst = con.prepareStatement("SELECT * FROM student_details WHERE student_id = ?");
        pst.setInt(1,getStudent_no());
        rs = pst.executeQuery();
        if (rs.next())
        {        
            return true;
        }
        else
            {
                return false;
            }
        }catch(SQLException sqle)
            {
                return false;
            }
        }
    
    public boolean save(){
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
       try{
        pst = con.prepareStatement("INSERT INTO student_details (student_id, first_name, last_name, gender, degree_programme) VALUES(?,?,?,?,?)");
            pst.setInt(1,getStudent_no());
            pst.setString(2, getFirst_name());
            pst.setString(3,getLast_name() );
            pst.setInt(4, processGender(getGender()));
            pst.setString(5, getProgramme());
            pst.executeUpdate();
                    }catch (SQLException ex){
    System.out.println("Error"+ ex.getMessage());
}


        return true;
    }
    /**
     * this method saves students number and name into the database 
     * @param name this is the student name
     * @param number this is the students number
     * @return returns true if data is saved successfully, false otherwise
     * @deprecated this method saves number and name into the database
     */
    public boolean save(String name, int number){
        //do your work here
        return true;
    }
    public boolean register()
    {
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
        
        try{
               
            pst = con.prepareStatement("INSERT INTO login_details VALUES(?,?)");
                pst.setInt(1,getStudent_no());
                pst.setString(2,getPass());
                pst.executeUpdate();
                con.commit();
           
            }catch(SQLException sqle)
                {
                return false;
                }
            return true;
        }
    public boolean removeRecord(int reg_number){
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
        try{
               
            pst = con.prepareStatement("DELETE FROM student_details WHERE student_id = ?");
                pst.setInt(1,getStudent_no());
                int xy = pst.executeUpdate();
                    if (xy == 1)
                    {
                        System.out.println("Student ID " + getStudent_no() 
                                + " has been remoed successfully." );
                        con.commit();
                    }else{
                        System.out.println("Student ID " + getStudent_no()
                                +" does not exist" );
                    }
            }catch(SQLException sqle)
                {
                    return false;
                }
        return true;
       
    }
    public boolean updateRecord(){
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
        
        // Check that the record exists
        try{
        
        pst = con.prepareStatement("SELECT * FROM student_details WHERE student_id = ?");
        pst.setInt(1,getStudent_no());
        rs = pst.executeQuery();
        // If the record exists, ask user which parameter they wuld like to update
        
        if (rs.next())
        {                    
        System.out.println("Which field would you like to update:\n"
                        + "1. Student No\n2. First name\n"
                        + "3. Last Name\n4. Gender\n5. Programme" );
        int param = 0;//DBConnector.scanner.nextInt();
        
        // Depending on their selection, use setter to set value, and update database
        switch (param)
                    {
                    case 1:
                        System.out.println("Enter new ID for the student:");
                        int i = DBConnector.scanner.nextInt();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "student_id = ? WHERE student_id = " 
                                + getStudent_no());
                        pst.setInt(1,i);
                        pst.executeUpdate();
                    break;
                    case 2 :
                        System.out.println("Enter new first name for the student:");
                        String name = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "first_name = ? WHERE student_id = " 
                                + getStudent_no());
                        pst.setString(1,name);
                        pst.executeUpdate();
                    break;
                    case 3 :
                        System.out.println("Enter new last name for the student:");
                        String lname = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "last_name = ? where student_id = " 
                                + getStudent_no());
                        pst.setString(1,lname);
                        pst.executeUpdate();
                    break;
                    case 4 :
                        System.out.println("Enter new gender for the student:");
                        String g = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "gender = ? where student_id =  " 
                                + getStudent_no());
                        pst.setInt(1,processGender(g));
                        pst.executeUpdate();
                    break;
                    case 5 :
                        System.out.println("Enter new degree programme for the student:");
                        String prog = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "degree_programme = ? where student_id = "
                                + getStudent_no());
                        pst.setString(1,prog);
                        pst.executeUpdate();
                    break;
                    default:
                        System.out.println("Invalid parameter selected");
                    }
        // Commit your changes, otherwise they will not be stored on the database
                    con.commit();
        }
        else{
            System.out.println("That StudentID does not exist on our system.");
        }
            }catch(SQLException sqle)
                {
                return false;
                }
        return true;
    }
    public boolean archiveRecord(int reg_number) {
        ResultSet rs = null; 
        PreparedStatement pst = null;
        Connection con = new Demo().connector();
        
        try{
                //Get the record from the database;
                
                pst = con.prepareStatement("SELECT * FROM student_details "
                        + "WHERE student_id = ?");
                pst.setInt(1,getStudent_no());
                rs = pst.executeQuery();
                
                // exctract the details to be filled in the old 
                // (archive table)
                while (rs.next())
                    {
                        setStudent_no(rs.getInt(1));
                        setFirst_name(rs.getString(2));
                        setLast_name(rs.getString(3));
                        setGender(processGender(rs.getInt(4)));
                        setProgramme(rs.getString(5));
                    
                        pst = con.prepareStatement("INSERT INTO student_old "
                                + "(student_id, first_name, last_name, gender, "
                                + "degree_programme) VALUES (?,?,?,?,?)");    
                            pst.setInt(1,getStudent_no());
                            pst.setString(2,getFirst_name());
                            pst.setString(3,getLast_name());
                            pst.setInt(4,processGender(getGender()));
                            pst.setString(5,getProgramme());
                            int rts = pst.executeUpdate();
                        
                        // Run as a transaction. Should the return status be 
                        //success, commit, else rollback.
                        if (rts == 1)
                            {                            
                                con.commit();
                            return true;
                            }else {
                                con.rollback();
                            return false;}
                            }
            }catch(SQLException sqle){
                System.out.print(sqle.getMessage());
                return false;
            }
        return true;
        }
}
