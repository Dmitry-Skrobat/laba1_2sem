import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        Volleyball studentVolleyball = new Volleyball();
        int quantity;
        int number;
        int flag;

        do {
            System.out.println("""
                    1 - Создать новую запись о студенте
                    2 - Внести изменения в уже имеющуюся запись
                    3 - Вывести все данные о студентах
                    4 - Вывести информацию обо всех студентах определенной группы
                    5 - Вывести топ самых успешных студентов с наивысшим по рейтингу средним баллом за прошедшую сессию
                    6 - Вывести количество студентов мужского и женского пола
                    7 - Вывести данные о студентах, которые не получают стипендию; учатся только на «хорошо» и «отлично»; учатся только на «отлично»
                    8 - Вывести данные о студентах, имеющих определенный номер в списке
                                        
                    ИДЗ №5
                    9 - Создать новую запись о студенте воллейбольной команды
                    10 - Вывести информацию обо всех студентах, имеющих разряд по волейболу
                    11 - Вывести информацию обо всех студентах женской сборной
                    12 - Вывести информацию обо всех студентах, k-ого курса
                    13 - Вывести информацию обо всех студентах, мужского пола
                    14 - Вывести информацию обо всех студентах, чей размер одежды больше S
                    """);

            System.out.print("Введите число: ");
            Scanner scanner = new Scanner(System.in);
            number = scanner.nextInt();

            switch (number) {
                case 1:
                    System.out.print("Введите какое количество студентов вы хотите добавить: ");
                    quantity = scanner.nextInt();

                    for (int i = 0; i < quantity; i++) {
                        student.createStudent("student.txt", true);
                    }
                    break;
                case 2:
                    String idFind;
                    show();
                    System.out.print("Введите id студента чьи данные хотите изменить: ");
                    idFind = scanner.next();
                    try {
                        File file = new File("student.txt");
                        File temp = new File("temp.txt");
                        Scanner scannerFile = new Scanner(file);
                        FileWriter writer = new FileWriter("temp.txt", true);
                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            if (data[0].equals("ID-" + idFind)) {
                                System.out.println("Введите новые данные: ");
                                student.createStudent("temp.txt", true);

                            } else {
                                writer.write(line + '\n');
                                writer.flush();
                            }
                        }
                        scannerFile.close();
                        writer.close();

                        if (file.exists()) {
                            if (file.delete()) {
                            }
                        }
                        if (temp.exists()) {
                            temp.renameTo(new File("student.txt"));
                        }
                        System.out.println("Данные успешно изменены");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    show();
                    break;
                case 3:
                    show();
                    break;
                case 4:
                    System.out.print("Введите номер группы, по которому отсортировать студентов: ");
                    String numGroup;
                    numGroup = scanner.next();
                    try {
                        File file = new File("student.txt");
                        Scanner scannerFile = new Scanner(file);
                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            if (data[4].equals("Номер группы:" + numGroup)) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();
                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    String[] info = new String[100];
                    double[] averageGrades = new double[100];
                    int count = 0;

                    try {
                        File file = new File("student.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] average = data[7].split(":");
                            info[count] = data[1];
                            averageGrades[count] = Double.parseDouble(average[1].trim());
                            count++;
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    for (int i = 0; i < count; i++) {
                        for (int j = i + 1; j < count; j++) {
                            if (averageGrades[i] < averageGrades[j]) {
                                double tempGrade = averageGrades[i];
                                averageGrades[i] = averageGrades[j];
                                averageGrades[j] = tempGrade;

                                String tempName = info[i];
                                info[i] = info[j];
                                info[j] = tempName;
                            }
                        }
                    }
                    System.out.println("Топ 3:");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(i + 1 + " - " + info[i] + " - " + averageGrades[i]);
                    }
                    break;

                case 6:
                    int countMen = 0;
                    int countWomen = 0;
                    try {
                        File file = new File("student.txt");
                        Scanner scannerFile = new Scanner(file);
                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            if (data[2].equals("Пол:мужской")) {
                                countMen += 1;
                            } else {
                                countWomen += 1;
                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    System.out.println("Студентов мужского пола: " + countMen);
                    System.out.println("Студентов женского пола: " + countWomen);
                    break;
                case 7:
                    System.out.print("""
                             1 - Вывести студентов, которые не получают стипендию
                             2 - Вывести студентов, которые учатся на 4 и 5
                             3 - Вывести студентов, которые учатся только на 5
                            """);
                    int value;
                    System.out.print("Введите число: ");
                    value = scanner.nextInt();
                    switch (value) {
                        case 1:
                            try {
                                File file = new File("student.txt");
                                Scanner scannerFile = new Scanner(file);

                                while (scannerFile.hasNextLine()) {
                                    String line = scannerFile.nextLine();
                                    String[] data = line.split(",");
                                    String[] gradeAndWords = data[6].split(":");
                                    String[] grades = gradeAndWords[1].split(" ");
                                    int countThree = 0;

                                    for (int i = 0; i < 8; i++) {
                                        if (grades[i].equals("3")) {
                                            countThree += 1;
                                        }
                                    }
                                    if (countThree > 0) {
                                        for (String item : data) {
                                            System.out.println(item);
                                        }
                                        System.out.println();
                                    }
                                }
                                scannerFile.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found.");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                File file = new File("student.txt");
                                Scanner scannerFile = new Scanner(file);

                                while (scannerFile.hasNextLine()) {
                                    String line = scannerFile.nextLine();
                                    String[] data = line.split(",");
                                    String[] gradeAndWords = data[6].split(":");
                                    String[] grades = gradeAndWords[1].split(" ");

                                    int countFour = 0;
                                    int countThree = 0;
                                    int countFive = 0;

                                    for (int i = 0; i < 8; i++) {
                                        if (grades[i].equals("4")) {
                                            countFour += 1;
                                        }
                                        if (grades[i].equals("3")) {
                                            countThree += 1;
                                        }
                                        if (grades[i].equals("5")) {
                                            countFive += 1;
                                        }
                                    }
                                    if (countFour > 0 && countThree == 0) {
                                        for (String item : data) {
                                            System.out.println(item);
                                        }
                                        System.out.println();
                                    }
                                }
                                scannerFile.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found.");
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                File file = new File("student.txt");
                                Scanner scannerFile = new Scanner(file);

                                while (scannerFile.hasNextLine()) {
                                    String line = scannerFile.nextLine();
                                    String[] data = line.split(",");
                                    String[] gradeAndWords = data[6].split(":");
                                    String[] grades = gradeAndWords[1].split(" ");
                                    int countFive = 0;

                                    for (int i = 0; i < 8; i++) {
                                        if (grades[i].equals("5")) {
                                            countFive += 1;
                                        }
                                    }
                                    if (countFive == 8) {
                                        for (String item : data) {
                                            System.out.println(item);
                                        }
                                        System.out.println();
                                    }
                                }
                                scannerFile.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("File not found.");
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case 8:
                    String idStudent;
                    System.out.print("Введите id студента информацию о котором хотите вывести: ");
                    idStudent = scanner.next();
                    try {
                        File file = new File("student.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            if (data[0].equals("ID-" + idStudent)) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();
                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;

                case 9:
                    System.out.print("Введите какое количество студентов вы хотите добавить: ");
                    quantity = scanner.nextInt();

                    for (int i = 0; i < quantity; i++) {
                        studentVolleyball.createStudentVolleyball("volleyball.txt", true);
                    }
                    break;
                case 10:
                    try {
                        File file = new File("volleyball.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] rang = data[4].split(":");
                            if (rang[1].equals("да")) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();

                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;

                case 11:
                    try {
                        File file = new File("volleyball.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] sex = data[1].split(":");
                            if (sex[1].equals("женский")) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();

                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;
                case 12:
                    int course;
                    System.out.print("Введите, о студентах какого курса вывести информацию: ");
                    course = scanner.nextInt();

                    try {
                        File file = new File("volleyball.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] courseNumebr = data[5].split(":");
                            if (courseNumebr[1].equals(Integer.toString(course))) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();

                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;

                case 13:
                    try {
                        File file = new File("volleyball.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] sex = data[1].split(":");
                            if (sex[1].equals("мужской")) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();

                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;
                case 14:
                    try {
                        File file = new File("volleyball.txt");
                        Scanner scannerFile = new Scanner(file);

                        while (scannerFile.hasNextLine()) {
                            String line = scannerFile.nextLine();
                            String[] data = line.split(",");
                            String[] size = data[3].split(":");
                            if (!size[1].equals("XXS") && !size[1].equals("XS") && !size[1].equals("S")) {
                                for (String item : data) {
                                    System.out.println(item);
                                }
                                System.out.println();

                            }
                        }
                        scannerFile.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        e.printStackTrace();
                    }
                    break;

            }
            System.out.print("Если хотите завершить программу нажмите 1, иначе 0: ");
            flag = scanner.nextInt();
        } while (flag == 0);

    }

    static void show() {
        try {
            File file = new File("student.txt");
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                String[] data = line.split(",");

                for (String item : data) {
                    System.out.println(item);
                }
                System.out.println();
            }
            scannerFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}


