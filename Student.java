import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Student {
    private int id = 0;
    private String fullName;
    private String sex;
    private int numberGroup;
    private int idInGroup;
    private int [] grades = new int [8];
    private String formEducation;
    private float average;


    public void createStudent(String fileName, boolean flag) {

        int count = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите полное имя студента: ");
        fullName = scanner.nextLine();

        System.out.print("Введите пол студента: ");
        sex = scanner.nextLine();

        System.out.print("Введите форму обучения студента: ");
        formEducation = scanner.nextLine();

        System.out.print("Введите номер группы студента: ");
        numberGroup = scanner.nextInt();

        System.out.print("Введите номер студента в группе: ");
        idInGroup = scanner.nextInt();


        System.out.println("Введите оценки студента за прошлый семестр: ");
        for(int i = 0;i<8;i++) {
            grades[i] = scanner.nextInt();
            if (grades[i]>5 || grades[i]<2){
                while(grades[i]>5 || grades[i]<2){
                    System.out.print("Введите корректную оценку: ");
                    grades[i] = scanner.nextInt();
                }
            }
            average+=grades[i];
        }

        average = average/8;
        int temp = 0;
        for(int i = 0;i<8;i++){
            if (grades[i] == 2){
                temp+=1;
            }
        }
        int countId = 0;
        try {
            File file = new File(fileName);
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                String[] data = line.split(",");
                if(data[0].equals("ID-" + String.valueOf(id))){
                    id++;
                }
                if(data[4].equals("Номер группы:" + numberGroup) && data[5].equals("Номер студента в группе:"+idInGroup)){
                    countId+=1;
                }
            }
            scannerFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }


        if(temp==0 && countId==0){
            try {
                FileWriter fileWriter = new FileWriter(fileName,true);
                fileWriter.write("ID-"+ id + ",");
                fileWriter.write("ФИО:" + fullName + ",");
                fileWriter.write("Пол:" + sex + ",");
                fileWriter.write("Форма обучения:" + formEducation + ",");
                fileWriter.write("Номер группы:" + numberGroup + ",");
                fileWriter.write("Номер студента в группе:" + idInGroup + ",");
                fileWriter.write("Оценки:");
                for (int i = 0; i < 8; i++) {
                    fileWriter.write(grades[i] + " ");
                }
                fileWriter.write(",");
                fileWriter.write("Средний балл:" + average);
                fileWriter.write("\n" );
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл.");
                e.printStackTrace();
            }
        }
        else if(countId>0){
            System.out.println("Студент с таким номер в группе уже есть, студент не будет добавлен в базу.");

        }
        else{
            System.out.println("Этот студент будет исключен. Профиль не будет сохранен в базе данных.");
        }
    }
}

