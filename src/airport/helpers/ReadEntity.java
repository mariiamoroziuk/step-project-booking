package airport.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReadEntity {
    public static int readInt(Scanner scanner, String message) {
        int res = -1;
        while (res == -1){
            System.out.println(message);
            String num = scanner.nextLine();
            try {
                res = Integer.parseInt(num);
            } catch (Exception e) {
                System.out.println("Это не число.");
            }
        }
        return res;
    }
    public static long readLong(Scanner scanner, String message) {
        long res = -1;
        while (res == -1){
            System.out.println(message);
            String num = scanner.nextLine();
            try {
                res = Long.parseLong(num);
            } catch (Exception e) {
                System.out.println("Это не ID.");
            }
        }
        return res;
    }
    public static LocalDateTime readDate(Scanner scanner) {
        LocalDateTime res = null;
        while (res == null){
            System.out.println("\nВведите дату вылета в формате ГГГГ-ММ-ДД");
            String date = scanner.nextLine();
            System.out.println("\nВведите время вылета в формате ЧЧ:ММ");
            String time = scanner.nextLine();
            try {
                if (date.matches("^\\d{4}-\\d{2}-\\d{2}$") && time.matches("^\\d{2}:\\d{2}$")){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    res = LocalDateTime.parse(date + " " + time, formatter);
                } else {
                    System.out.println("\nНеверный формат времени или даты, попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("\nОшибка при парсинге даты и времени. Попробуйте еще раз");
            }
        }
        return res;
    }
}
