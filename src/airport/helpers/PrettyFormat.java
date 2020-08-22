package airport.helpers;

import airport.entity.Booking;
import airport.entity.Flight;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrettyFormat {
    public static void printMenuItems(){
        System.out.println("\nВам доступны такие команды:");
        System.out.println("1. Онлайн-табло");
        System.out.println("2. Посмотреть информацию о рейсе");
        System.out.println("3. Поиск и бронировка рейса");
        System.out.println("4. Отменить бронирование");
        System.out.println("5. Мои бронирования");
        System.out.println("6. Завершить сессию");
        System.out.println("7. Выход");
    }
    public static void listFlightTable(List<Flight> flights){
        System.out.printf("%n%-8s%-37s%-37s%n","#Рейс","Отправление","Прибытие");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Flight flight : flights) {
            if (flight != null){
                System.out.printf("%-8d%-15s%-22s%-15s%-22s%n",
                        flight.getId(),
                        flight.getDeparturePlace(),
                        flight.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                        flight.getArrivalPlace(),
                        flight.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")));
            } else {
                System.out.println("Указанный рейс не найдено");
            }
        }
    }
    public static void flightTable(Flight flight, int bookingCount){
        if (flight != null){
            System.out.printf("%n%-8s%-37s%-37s%-10s%n", "#Рейс", "Отправление", "Прибытие", "Cвободных мест");
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%-8d%-15s%-22s%-15s%-22s%-10d%n",
                    flight.getId(),
                    flight.getDeparturePlace(),
                    flight.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                    flight.getArrivalPlace(),
                    flight.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                    flight.getTotalSeats() - bookingCount);
        } else {
            System.out.println("Указанный рейс не найдено");
        }
    }

    public static void connectingFlightTable(Flight flight1, List<Flight> listFlights){
        if (listFlights.size() > 0) {
            System.out.printf("%-8d%-15s%-22s%-15s%-22s",
                    flight1.getId(),
                    flight1.getDeparturePlace(),
                    flight1.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                    flight1.getArrivalPlace(),
                    flight1.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")));

            for (int i = 0; i < listFlights.size(); i++) {
                if (i != 0) {
                    System.out.printf("%-82s", "");
                }
                System.out.printf("%-12s%-8d%-15s%-22s%-15s%-22s%n",
                        "|пересадка|",
                        listFlights.get(i).getId(),
                        listFlights.get(i).getDeparturePlace(),
                        listFlights.get(i).getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                        listFlights.get(i).getArrivalPlace(),
                        listFlights.get(i).getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")));
            }
        }
    }
    public static void bookingTableHeader(){
            System.out.printf("%n%-18s%-10s%-37s%-37s%n", "#Бронирование", "#Рейс", "Отправление", "Прибытие");
            System.out.println("-----------------------------------------------------------------------------------------");
    }
    public static void connectingTableHeader(){
        System.out.printf("%n%-10s%-37s%-37s%-12s%-10s%-37s%-37s%n", "#Рейс", "Отправление", "Прибытие", "", "#Рейс", "Отправление", "Прибытие");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void bookingTable(Flight flight, Booking booking){
        if (flight != null && booking != null) {
            System.out.printf("%-18d%-10d%-15s%-22s%-15s%-22s%n",
                    booking.getId(),
                    flight.getId(),
                    flight.getDeparturePlace(),
                    flight.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")),
                    flight.getArrivalPlace(),
                    flight.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm   dd-MM-uuuu")));
        } else {
            System.out.println("Указанный рейс или бронирование не найдено");
        }
    }
}
