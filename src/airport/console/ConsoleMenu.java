package airport.console;

import airport.AirportContext;
import airport.dao.Database;
import airport.entity.Booking;
import airport.entity.Flight;
import airport.entity.User;
import airport.exception.NoSuchUserFoundException;
import airport.exception.UnexpectedConsoleInputException;
import airport.helpers.PrettyFormat;
import airport.helpers.ReadEntity;
import airport.logger.AirportLogger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        while (AirportContext.currentUser == null) {
            try {
                System.out.println("\nВведите логин:");
                login = scanner.nextLine();
                System.out.println("\nВведите пароль:");
                password = scanner.nextLine();

                AirportContext.currentUser = AirportContext.userController.getUserByLoginAndPassword(login, password);
                if (AirportContext.currentUser == null) {
                    AirportLogger.error("Пользователь с таким логином и паролем не найден в системе");
                    throw new NoSuchUserFoundException();
                }
            } catch (NoSuchUserFoundException e){
                System.err.println(e.getMessage());
            }
        }
        menu(scanner);
    }
    public static void menu(Scanner scanner) {
        boolean run = true;
        while (run) {
            PrettyFormat.printMenuItems();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    try {
                        onlineTable();
                    } catch (UnexpectedConsoleInputException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "2":
                    try{
                        showFlight(scanner);
                    } catch (UnexpectedConsoleInputException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "3":
                    int bookingCount = -1;
                    try{
                        bookingCount = findFlights(scanner);
                    } catch (UnexpectedConsoleInputException e){
                        System.err.println(e.getMessage());
                    }
                    long flightNumber = ReadEntity.readLong(scanner, "\nВыберите один из рейсов, указав его номер. Для возврата в главное меню введите ноль");
                    if (flightNumber == 0){
                        break;
                    } else {
                        try{
                            bookFlight(scanner, flightNumber, bookingCount);
                        } catch (UnexpectedConsoleInputException e){
                            System.err.println(e.getMessage());
                        }
                    }
                    break;
                case "4":
                    try{
                        deleteBooking(scanner);
                    } catch (UnexpectedConsoleInputException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "5":
                    try{
                        showBooking();
                    } catch (UnexpectedConsoleInputException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case "6":
                    AirportContext.currentUser = null;
                    run = false;
                    start();
                    break;
                case "7":
                    run = false;
                    end();
                    break;
                default:
                    System.out.println("Неизвестная команда, попробуйте ввести еще раз");
                    break;
            }
        }
    }
    public static void onlineTable(){
        List<Flight> flights = AirportContext.flightController.getFlightByDepartureTimeAndPlace(LocalDateTime.now(), 24, "КИЕВ");
        if (flights.size() > 0) {
            PrettyFormat.listFlightTable(flights);
        } else {
            AirportLogger.error("Поиск рейсов по времени, месту вылета и периода поиска");
            throw new UnexpectedConsoleInputException("Авиарейсы не найдены");
        }
    }
    public static void showFlight(Scanner scanner){
        long flightId = ReadEntity.readLong(scanner, "Введите айди рейса");
        Flight flightById = AirportContext.flightController.getFlightById(flightId);
        int countBookingByFlight = AirportContext.bookingController.getCountBookingByFlightId(flightId);
        if (flightById != null) {
            PrettyFormat.flightTable(flightById, countBookingByFlight);
        } else {
            AirportLogger.error("Поиск рейсов по введенному пользователем айди рейса");
            throw new UnexpectedConsoleInputException("Авиарейс не найден");
        }
    }
    public static int findFlights(Scanner scanner){
        System.out.println("Введите место отправления:");
        String departurePlace = scanner.nextLine().trim().toUpperCase();
        System.out.println("Введите место назначения:");
        String arrivalPlace = scanner.nextLine().trim().toUpperCase();
        int bookingCount = ReadEntity.readInt(scanner, "Введите колличество пассажиров:");
        LocalDateTime departureTime= ReadEntity.readDate(scanner);

        //прямые рейсы
        List<Flight> flightByDepartureArrival = AirportContext.flightController.getFlightByDepartureArrival(departureTime, 24, departurePlace, arrivalPlace);
        if (flightByDepartureArrival.size() > 0) {
            List<Flight> flightsEmptySeats = AirportContext.bookingController.getFlightsEmptySeats(flightByDepartureArrival, bookingCount);
            if (flightsEmptySeats.size() > 0){
                PrettyFormat.listFlightTable(flightsEmptySeats);
            }else {
                AirportLogger.error("Поиск прямых рейсов со свободными местами по введенным пользователем местом, временем вылета и местом прилета");
                throw new UnexpectedConsoleInputException("Прямые авиарейсы со свободными местами не найдены");
            }
        } else {
            AirportLogger.error("Поиск прямых рейсов по введенным пользователем местом, временем вылета и местом прилета");
            throw new UnexpectedConsoleInputException("Прямые авиарейсы по введенным Вами параметрам не найдены");
        }

        //стыковочные рейсы
        List<Flight> flightByDepartureTimeAndPlace = AirportContext.flightController.getFlightByDepartureTimeAndPlace(departureTime, 24, departurePlace);
        if (flightByDepartureTimeAndPlace.size() > 0){
            List<Flight> flightsByDeparture = AirportContext.bookingController.getFlightsEmptySeats(flightByDepartureTimeAndPlace, bookingCount);
            if (flightsByDeparture.size() > 0){
                System.out.println("\nСтыковочные рейсы:");
                PrettyFormat.connectingTableHeader();
                flightsByDeparture.forEach(flight-> PrettyFormat.connectingFlightTable(
                        flight, AirportContext.flightController.getFlightByDepartureArrival(flight.getArrivalTime(), 12, flight.getArrivalPlace(), arrivalPlace )
                ));
            } else {
                AirportLogger.error("Поиск стыковочных рейсов со свободными местами по введенным пользователем местом, временем вылета и местом прилета");
                throw new UnexpectedConsoleInputException("Стыковочные авиарейсы со свободными местами не найдены");
            }
        } else {
            AirportLogger.error("Поиск стыковочных рейсов по введенным пользователем местом, временем вылета и местом прилета");
            throw new UnexpectedConsoleInputException("Стыковочные авиарейсы по введенным Вами параметрам не найдены");
        }
        return bookingCount;
    }
    public static void bookFlight(Scanner scanner, long flightNumber, int bookingCount){
        List<User> passengers = new ArrayList<>();
        if (ReadEntity.readInt(scanner, "\nОдним из пассажиров будует Вы? \n1. Да\n2. Нет") == 1){
            passengers.add(AirportContext.currentUser);
        }
        String name;
        String surname;

        while(passengers.size() != bookingCount){
            System.out.println("\nВведите имя пассажира:");
            name = scanner.nextLine();
            System.out.println("\nВведите фамилию пассажира:");
            surname = scanner.nextLine();
            User user = AirportContext.userController.getUserByName(name, surname);

            if(user == null){
                passengers.add(AirportContext.userController.saveUser(new User(name, surname)));
            } else {
                passengers.add(user);
            }
        }
        if (AirportContext.flightController.getFlightById(flightNumber) != null){
            passengers.forEach(passenger -> AirportContext.bookingController.saveBooking(new Booking(flightNumber, passenger.getId())));
            System.out.println("Ваше бронирование сохранено");
        } else {
            AirportLogger.error("Поиск рейса по введенному пользователем айди");
            throw new UnexpectedConsoleInputException("Указаный Вами рейс не найден");
        }
    }
    public static void deleteBooking(Scanner scanner){
        long bookingId = ReadEntity.readLong(scanner, "Введите айди бронирования");
        if (AirportContext.bookingController.deleteBookingById(bookingId)){
            System.out.printf("\nБронирование %d удалено\n", bookingId);
        } else {
            AirportLogger.error("Поиск бронирования по введенному пользователем айди при удалении");
            throw new UnexpectedConsoleInputException("Указанное Вами бронирование не найдено");
        }
    }
    public static void showBooking(){
        List<Booking> bookingOfUser = AirportContext.bookingController.getBookingsByUserId(AirportContext.currentUser.getId());
        if (bookingOfUser.size() > 0){
            System.out.println("Вами забронированы следующие авиарейсы:");
            PrettyFormat.bookingTableHeader();
            bookingOfUser.forEach(b -> PrettyFormat.bookingTable(AirportContext.flightController.getFlightById(b.getFlightId()), b));
        } else {
            AirportLogger.error("Поиск бронирования по айди текущего пользователя для вывода пользователю");
            throw new UnexpectedConsoleInputException("Бронирования не найдены");
        }
    }
    public static void end(){
        Database.writeFlight(AirportContext.flightController.getAllFlights());
        Database.writeUser(AirportContext.userController.getAllUsers());
        Database.writeBooking(AirportContext.bookingController.getAllBookings());
    }
}

