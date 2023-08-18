import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датаРождения(формат dd.mm.yyyy) номерТелефона(без +7) пол(m/f)");
        String input = scanner.nextLine();
        String[] data = input.split(" ");
        if (data.length != 6) {
            System.out.println("Ошибка: неверное количество параметров");
            return;
        }
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты рождения");
            return;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат номера телефона");
            return;
        }
        char gender;
        if (data[5].length() == 1 && (data[5].charAt(0) == 'f' || data[5].charAt(0) == 'm')) {
            gender = data[5].charAt(0);
        } else {
            System.out.println("Ошибка: неверный формат пола");
            return;
        }
        try (FileWriter writer = new FileWriter(lastName + ".txt", true)) {
            writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + phoneNumber + " " + gender + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
