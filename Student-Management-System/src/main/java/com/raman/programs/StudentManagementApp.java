package com.raman.programs;

import com.raman.cfg.AppConfig;
import com.raman.dao.DaoException;
import com.raman.dao.StudentManagementDao;
import com.raman.entity.Students;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
                updateStudent(dao, in);
            } else if (choice == 3) {
                removeStudent(dao, in);
            } else if (choice == 4) {
                searchStudent(dao, new Students(), in);
            } else if (choice == 5) {
                displayAllStudents(dao);

            } else {
                System.out.println("Exiting App.....");
                break;
            }

        }

        ctx.close();
        in.close();
        System.exit(0);
    }




    //Adding Student
    private static void addStudent(StudentManagementDao dao, Scanner in) {
        Students st = new Students();
        System.out.println("_____________________________________");
        System.out.println("\t\tAdding a New Student : ");
        System.out.print("Enter First Name : ");
        in.nextLine();
        st.setFirstName(in.nextLine());
        System.out.print("Enter Last Name : ");
        st.setLastName(in.nextLine());

        System.out.print("Enter Date of Birth (yyyy-mm-dd) : ");
        String inputDate = in.nextLine();
        if (inputDate.isEmpty()) {
            st.setDateOfBirth(null);
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(inputDate, dtf);
            st.setDateOfBirth(dateOfBirth);
        }

        System.out.print("Enter Contact Number : ");
        long cont = in.nextLong();
        st.setContact(cont + "");

        System.out.print("Enter Address : ");
        in.nextLine();
        st.setAddress(in.nextLine());

        System.out.print("Enter Course opted : ");
        st.setCourse(in.nextLine());

        System.out.print("Enter overall grades : ");
        String grade = in.nextLine();
        if (grade.isEmpty()) {
            st.setOverallGrades(null);
        } else {
            st.setOverallGrades(Double.parseDouble(grade));

        }
        try {
            dao.insertStudent(st);
            System.out.println("New Student at Position [" + dao.countStudents() + "] inserted....");
        } catch (DaoException e) {
            System.out.println("There was an Error in adding new Student." + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unknown Error Occurred.");
        }
        System.out.println("|___________________________________|");
    }

    //Updating Student
    private static void updateStudent(StudentManagementDao dao, Scanner in) {
        Students st = new Students();
        System.out.println("_____________________________________");
        System.out.print("Enter Student_ID : ");
        Integer stId = in.nextInt();
        try {
            st = dao.getStudent(stId);
        } catch (DaoException e) {
            System.out.println("Student Not Found." + e.getMessage());
        }
        searchStudent(dao, st, in);
        System.out.print("Enter Field To Update :");
        int choice = in.nextInt();

        switch (choice) {
            case 1: {
                System.out.print("Enter First Name : ");
                in.nextLine();
                st.setFirstName(in.nextLine());
            }
            case 2: {
                System.out.print("Enter Last Name : ");
                in.nextLine();
                st.setLastName(in.nextLine());
            }
            case 3: {
                System.out.print("Enter New Date of Birth (yyyy-mm-dd) : ");
                in.nextLine();
                String inputDate = in.nextLine();
                if (inputDate.isEmpty()) {
                    st.setDateOfBirth(null);
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateOfBirth = LocalDate.parse(inputDate, dtf);
                    st.setDateOfBirth(dateOfBirth);
                }
            }
            case 4: {
                System.out.print("Enter Contact Number : ");
                in.nextLine();
                long cont = in.nextLong();
                st.setContact(cont + "");
            }
            case 5: {
                System.out.print("Enter Address : ");
                in.nextLine();
                st.setAddress(in.nextLine());
            }
            case 6: {
                System.out.print("Enter Course opted : ");
                in.nextLine();
                st.setCourse(in.nextLine());
            }
            case 7: {
                System.out.print("Enter overall grades : ");
                in.nextLine();
                String grade = in.nextLine();
            }
            default: {
                System.out.println("No Changes Being Made..");
            }
        }

        try {
            dao.updateStudent(st);
            System.out.println("Update Success..");
        } catch (DaoException e) {
            System.out.println("Updating failed...");
        }

        System.out.println("|___________________________________|");
    }

    private static void removeStudent(StudentManagementDao dao, Scanner in) {
        System.out.println("_____________________________________");
        System.out.print("Enter Student ID :");
        Integer stId = in.nextInt();
        try {
            dao.deleteStudent(stId);
            System.out.println("Removed ->" + stId);
        } catch (DaoException e) {
            System.out.println("Student Not Found. " + e.getMessage());
        }
        System.out.println("|___________________________________|");
    }

    private static void searchStudent(StudentManagementDao dao, Students st, Scanner in) {
        System.out.println("_____________________________________");
        System.out.print("Enter Student ID : ");
        Integer stId = in.nextInt();
        try {
            st = dao.getStudent(stId);
            System.out.println("Student Found....");
            printStudent(st);
        } catch (DaoException e) {
            System.out.println("Student Not Exists. " + e.getMessage());
        }
        System.out.println("|___________________________________|");
    }
    private static void printStudent(Students st) {
        System.out.println("_______________________________");
        System.out.println("| Student ID : " + st.getStudentId());
        System.out.println("| Name : " + st.getFirstName() + " " + st.getLastName());
        System.out.println("| D.O.B : " + st.getDateOfBirth());
        System.out.println("| Contact : " + st.getContact());
        System.out.println("| Address : " + st.getAddress());
        System.out.println("| Course : " + st.getCourse());
        System.out.println("| Overall Grades : " + st.getOverallGrades());
        System.out.println("|______________________________|");
    }

    private static void displayAllStudents(StudentManagementDao dao) {
        System.out.println("_____________________________________");
        System.out.println("List Of All Students : ");
        try {
            List<Students>sts = dao.getAllStudents();
            sts.forEach(StudentManagementApp::printStudent);

        } catch (DaoException e) {
            System.out.println("No Students added. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown Error Occurred..");
        }

        System.out.println("|___________________________________|");
    }
}
