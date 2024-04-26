
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Volleyball {

    private String name;
    private String sex;
    private int age;
    private String clothingSize;
    private String rang;
    private int courseNumber;


    void createStudentVolleyball(String fileName, boolean flag) {

        int count = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите полное имя студента: ");
        name = scanner.nextLine();

        System.out.print("Введите пол студента: ");
        sex = scanner.nextLine();

        System.out.print("Введите возраст студента: ");
        age = scanner.nextInt();

        System.out.print("Введите размер одежды студента(XXS,XS,S,M,L,XL,XXL,3XL,4XL,5XL): ");
        clothingSize = scanner.next();

        System.out.print("Введите 'да', если у студента есть разряд по волейболу, иначе введите 'нет ': ");
        rang = scanner.next();


        System.out.print("Введите курс студента: ");
        courseNumber = scanner.nextInt();


        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("ФИО:" + name + ",");
            fileWriter.write("Пол:" + sex + ",");
            fileWriter.write("Возраст:" + age + ",");
            fileWriter.write("Размер одежды:" + clothingSize + ",");
            fileWriter.write("Разряд:" + rang + ",");
            fileWriter.write("Номер курса:" + courseNumber);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
            e.printStackTrace();
        }


    }
}
