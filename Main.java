package BaiThiModule2;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        do {
            System.out.println("----------PROGRAMING STUDENTS MANAGER----------");
            System.out.println();
            System.out.println("Please select function by number to continue: ");
            System.out.println("1. Show list about students :");
            System.out.println("2. Add by Id:");
            System.out.println("3. Update by Id:");
            System.out.println("4. Delete by Id:");
            System.out.println("5. Sort by Average Score:");
            System.out.println("6. Read File :");
            System.out.println("7. Write File :");
            System.out.println("8. Exit :");
            System.out.println("Select your function:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice <= 0 || choice > 8) {
                    throw new RuntimeException();
                }
                switch (choice) {
                    case 1:
                        studentManager.displayList();
                        break;
                    case 2:
                        studentManager.add(scanner);
                        break;
                    case 3:
                        studentManager.update(scanner);
                        break;
                    case 4:
                        studentManager.delete(scanner);
                        break;
                    case 5:
                        studentManager.sortByAvgScore();
                        break;
                    case 8:
                        System.exit(8);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter your selection!");
            }
        } while (true);
    }
}
