package BaiThiModule2;

import java.io.*;
import java.util.*;

public class StudentManager implements Serializable {
    public ArrayList<Student> students;

    public StudentManager() {
        students = readFile();
    }

    public void showListStudent(Scanner scanner) {
//        System.out.println("Enter info for 5 students:");
//        for (int i = 0; i < 2; i++) {
//            add(scanner);
//        }
        displayList();
        System.out.println();
    }

    public void add(Scanner scanner) {
        try {
            System.out.println("Enter student id: ");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.println("Enter student name: ");
            String name = scanner.nextLine();
            System.out.println("Enter student age: ");
            Integer age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter student gender: ");
            String gender = scanner.nextLine();
            System.out.println("Enter student address: ");
            String address = scanner.nextLine();
            System.out.println("Enter student average score: ");
            Double avgScore = Double.parseDouble(scanner.nextLine());
            Student student = new Student(id, name, age, gender, address, avgScore);
            students.add(student);
            writeFile();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Scanner scanner) {
        try {
            System.out.println("Enter id student your update: ");
            Long id = Long.parseLong(scanner.nextLine());
            Student studentUpdate;
            if ((studentUpdate = checkExist(id)) != null) {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!name.equals("")) {
                    studentUpdate.setName(name);
                }
                System.out.println("Enter new age: ");
                String age = scanner.nextLine();
                if (!age.equals("")) {
                    studentUpdate.setAge(Integer.parseInt(age));
                }
                System.out.println("Enter new gender: ");
                String gender = scanner.nextLine();
                if (!gender.equals("")) {
                    studentUpdate.setGender(gender);
                }
                System.out.println("Enter new address: ");
                String address = scanner.nextLine();
                if (!address.equals("")) {
                    studentUpdate.setAddress(address);
                }
                System.out.println("Enter new average score: ");
                String avgScore = scanner.nextLine();
                if (!avgScore.equals("")) {
                    studentUpdate.setAvgScore(Double.parseDouble(avgScore));
                }
                writeFile();
            } else {
                System.err.println("Have no Student");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        try {
            System.out.println("Enter id student your delete: ");
            Long id = Long.parseLong(scanner.nextLine());
            Student studentDelete;
            if ((studentDelete = checkExist(id)) != null) {
                students.remove(studentDelete);
                writeFile();
            } else {
                System.err.println("Have no Student!");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void displayList() {
        System.out.printf("%-10s%-10s%-15s%-15s%-15s%s", "ID", "Name", "Age", "Gender", "Address", "Average Score");
        for (Student s : students) {
            s.display();
        }
    }

    public void sortByAvgScore() {
        try {
            students.sort(Comparator.comparing(Student::getAvgScore));
            for (Student s : students) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("");
        }
    }

    public Student checkExist(Long id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private void writeFile() {
        File file = new File("Module2/src/BaiThiModule2/data/students.csv");
        try (ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(students);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
//    public void writeFileCSV() {
//        FileWriter fileWriter;
//        try {
//            fileWriter = new FileWriter("student.csv");
//            for (Student s : students) {
//                fileWriter.append(String.valueOf(s.getId()));
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(country.getCode());
//                fileWriter.append(COMMA_DELIMITER);
//                fileWriter.append(country.getName());
//                fileWriter.append(NEW_LINE_SEPARATOR);
//            }
//
//            System.out.println("CSV file was created successfully !!!");
//
//        } catch (Exception e) {
//            System.out.println("Error in CsvFileWriter !!!");
//            e.printStackTrace();
//        } finally {
//            try {
//                fileWriter.flush();
//                fileWriter.close();
//            } catch (IOException e) {
//                System.out.println("Error while flushing/closing fileWriter !!!");
//                e.printStackTrace();
//            }
//        }
//    }

    private ArrayList<Student> readFile() {
        File file = new File("Module2/src/BaiThiModule2/data/students.csv");
        ArrayList<Student> studentsArrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                studentsArrayList = (ArrayList<Student>) objectInputStream.readObject();
            }
            return studentsArrayList;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return studentsArrayList;
    }

}
