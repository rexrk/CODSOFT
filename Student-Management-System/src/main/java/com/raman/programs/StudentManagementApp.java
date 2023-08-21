package com.raman.programs;

import com.raman.cfg.AppConfig;
import com.raman.dao.DaoException;
import com.raman.dao.StudentManagementDao;
import com.raman.entity.Students;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx;
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentManagementDao dao = ctx.getBean("stDao", StudentManagementDao.class);

        Scanner in = new Scanner(System.in);

        //Main InterFace
        int choice = 0;
        while (choice < 6) {
            System.out.println("_____________________________________");
            System.out.println("|\t\tStudent Management App\t\t|");
            System.out.println("|___________________________________|");
            System.out.println("| Select an option :\t\t\t\t|");
            System.out.println("|  1. Add a Student.\t\t\t\t|");
            System.out.println("|  2. Update a Student.\t\t\t\t|");
            System.out.println("|  3. Remove a Student.\t\t\t\t|");
            System.out.println("|  4. Search a Student.\t\t\t\t|");
            System.out.println("|  5. Display all Students.\t\t\t|");
            System.out.println("|  6. Exit App.\t\t\t\t\t\t|");
            System.out.println("|___________________________________|");
            System.out.print("Enter Option No. : ");
            choice = in.nextInt();

            if (choice == 1) {
                addStudent(dao, in);

            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {


            } else {
                System.out.println("Exiting App.....");
                break;
            }

        }

        ctx.close();

    }

    private static void addStudent(StudentManagementDao dao, Scanner in) {
        Students st = new Students();
        System.out.println("\t\tAdding a New Student :");
        System.out.print("Enter First Name :");
        in.nextLine();
        st.setFirstName(in.nextLine());
        System.out.print("Enter Last Name :");
        st.setLastName(in.nextLine());

        System.out.print("Enter Date of Birth(yyyy-mm-dd) :");
        String inputDate = in.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirth = LocalDate.parse(inputDate, dtf);
        st.setDateOfBirth(dateOfBirth);

        System.out.print("Enter Contact Number :");
        long cont = in.nextLong();
        st.setContact(cont + "");

        System.out.print("Enter Address :");
        in.nextLine();
        st.setAddress(in.nextLine());

        System.out.print("Enter Course opted :");
        st.setCourse(in.nextLine());

        System.out.print("Enter overall grades :");
        st.setOverallGrades(in.nextDouble());
        try {
            dao.insertStudent(st);
            System.out.println("New Student with Id :" + st.getStudentId() + " inserted");
        } catch (DaoException e) {
            System.out.println("There was an Error in adding new Student." + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unknown Error Occurred.");
        }
    }

}
