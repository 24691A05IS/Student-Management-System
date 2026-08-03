import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = DBConnection.getConnection();

        if (con == null) {
            System.out.println("Database Connection Failed!");
            return;
        }

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

               case 1:

    System.out.print("Enter Student ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Department: ");
    String dept = sc.nextLine();

    System.out.print("Enter Year: ");
    int year = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Email: ");
    String email = sc.nextLine();

    System.out.print("Enter Phone: ");
    long phone = sc.nextLong();

    try {

        String sql = "INSERT INTO MITS VALUES(?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, dept);
        ps.setInt(4, year);
        ps.setString(5, email);
        ps.setLong(6, phone);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Added Successfully.");
        } else {
            System.out.println("Student Not Added.");
        }

        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    break;
   
              case 2:
    try {
        String sql = "SELECT * FROM MITS";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== STUDENT DETAILS =====");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.println("Student ID : " + rs.getInt("stu_id"));
            System.out.println("Name       : " + rs.getString("name"));
            System.out.println("Department : " + rs.getString("dept"));
            System.out.println("Year       : " + rs.getInt("year"));
            System.out.println("Email      : " + rs.getString("email"));
            System.out.println("Phone      : " + rs.getLong("phone"));
            System.out.println("----------------------------");
        }

        if (!found) {
            System.out.println("No Student Records Found.");
        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
				case 3:

    System.out.print("Enter Student ID to Search: ");
    int searchId = sc.nextInt();

    try {

        String sql = "SELECT * FROM MITS WHERE stu_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, searchId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("\n===== STUDENT FOUND =====");
            System.out.println("Student ID : " + rs.getInt("stu_id"));
            System.out.println("Name       : " + rs.getString("name"));
            System.out.println("Department : " + rs.getString("dept"));
            System.out.println("Year       : " + rs.getInt("year"));
            System.out.println("Email      : " + rs.getString("email"));
            System.out.println("Phone      : " + rs.getLong("phone"));

        } else {

            System.out.println("Student Not Found.");

        }

        rs.close();
        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    
                    break;
				case 4:

    System.out.print("Enter Student ID to Update: ");
    int updateId = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter New Name: ");
    String newName = sc.nextLine();

    System.out.print("Enter New Department: ");
    String newDept = sc.nextLine();

    System.out.print("Enter New Year: ");
    int newYear = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter New Email: ");
    String newEmail = sc.nextLine();

    System.out.print("Enter New Phone: ");
    long newPhone = sc.nextLong();

    try {

        String sql = "UPDATE MITS SET name=?, dept=?, year=?, email=?, phone=? WHERE stu_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, newName);
        ps.setString(2, newDept);
        ps.setInt(3, newYear);
        ps.setString(4, newEmail);
        ps.setLong(5, newPhone);
        ps.setInt(6, updateId);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Updated Successfully.");
        } else {
            System.out.println("Student ID Not Found.");
        }

        ps.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    break;

                case 5:
    try {
        System.out.print("Enter Student ID to Delete: ");
        int deleteId = sc.nextInt();

        String deleteQuery = "DELETE FROM MITS WHERE STU_ID=?";

        PreparedStatement ps5 = con.prepareStatement(deleteQuery);
        ps5.setInt(1, deleteId);

        int deleted = ps5.executeUpdate();

        if (deleted > 0) {
            System.out.println("Student Deleted Successfully.");
        } else {
            System.out.println("Student ID Not Found.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    break;

                case 6:
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
