package airport.helpers;

import airport.dao.Database;
import airport.entity.Booking;
import airport.entity.Flight;
import airport.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DBInit {
    public static List<Flight> flights = new ArrayList<Flight>();
    public static List<User> users = new ArrayList<User>();
    public static List<Booking> bookings = new ArrayList<Booking>();

    public static void flightsInit(){
        Random random = new Random();

        for (int i = 1; i <= 5000; i++) {
            int indexDeparturePlace = random.nextInt(cities.length);
            int indexArrivalPlace = -1;
            do {
                indexArrivalPlace = random.nextInt(cities.length);
            } while (indexArrivalPlace == indexDeparturePlace);

            LocalDateTime departureTime = LocalDateTime.now().plusHours(random.nextInt(24 * 15));
            LocalDateTime arrivalTime = departureTime.plusHours(random.nextInt(5) + 1);

            flights.add(new Flight((long) i, cities[indexDeparturePlace], cities[indexArrivalPlace], departureTime, arrivalTime, 20));
        }
    }

    public static void usersInit(){
        users.add(new User( new Date().getTime() + 1,"userName", "userSurname", "login", "password"));
        users.add(new User(new Date().getTime() + 2, "Some", "One", "login2", "password2"));
        users.add(new User(new Date().getTime() + 3,"Мария", "Морозюк", "1111", "1111"));

    }

    public static String[] cities = {
            "АМСТЕРДАМ",
            "АФИНЫ",
            "БЕРН",
            "БРАТИСЛАВА",
            "БРЮССЕЛЬ",
            "БУДАПЕШТ",
            "БУХАРЕСТ",
            "ВЕНА",
            "ВИЛЬНЮС",
            "ДУБЛИН",
            "КИЕВ",
            "КОПЕНГАГЕН",
            "ЛИССАБОН",
            "ЛЮКСЕМБУРГ",
            "МАДРИД",
            "ОСЛО",
            "ПРАГА",
            "РЕЙКЬЯВИК",
            "РИГА",
            "САН-МАРИНО",
            "СОФИЯ",
            "СТОКГОЛЬМ",
            "ТАЛЛИН",
            "ХЕЛЬСИНКИ"
    };

    public static void initAll(){
        flightsInit();
        Database.writeFlight(flights);
        usersInit();
        Database.writeUser(users);
        bookings.add(new Booking(new Date().getTime(), (long) 3333, users.get(2).getId()));
        Database.writeBooking(bookings);
    }
}
