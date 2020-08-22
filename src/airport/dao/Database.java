package airport.dao;

import airport.entity.Booking;
import airport.entity.Flight;
import airport.entity.User;
import airport.logger.AirportLogger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String flightPath = "database/flight.txt";
    private static final String bookingPath = "database/booking.txt";
    private static final String userPath = "database/user.txt";

    public static void writeFlight(List<Flight> flights) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(flightPath)))
        {
            oos.writeObject(flights);
        }
        catch(Exception e){
            AirportLogger.error("запись списка рейсов в файл");
            e.printStackTrace();
        }
    }
    public static List<Flight> readFlight() {
        List<Flight> flights = new ArrayList<Flight>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(flightPath)))
        {
            AirportLogger.info("считывание списка рейсов с файла");
            flights=((List<Flight>)ois.readObject());
            AirportLogger.info("считано с файла рейсов:"+flights.size());
        }
        catch(Exception e){
            AirportLogger.error("считывание списка рейсов с файла");
            e.printStackTrace();
        }
        return flights;
    }
    public static void writeUser(List<User> users) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userPath)))
        {
            oos.writeObject(users);
        }
        catch(Exception e){
            AirportLogger.error("запись списка пользователей в файл");
            e.printStackTrace();
        }
    }
    public static List<User> readUser() {
        List<User> users = new ArrayList<User>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userPath)))
        {
            AirportLogger.info("считывание списка пользователей с файла");
            users=((List<User>)ois.readObject());
            AirportLogger.info("считано с файла пользователей:"+users.size());
        }
        catch(Exception e){
            AirportLogger.error("считывание списка пользователей с файла");
            e.printStackTrace();
        }
        return users;
    }
    public static void writeBooking(List<Booking> bookings) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookingPath)))
        {
            oos.writeObject(bookings);
        }
        catch(Exception e){
            AirportLogger.error("запись списка бронирований в файл");
            e.printStackTrace();
        }
    }
    public static List<Booking> readBooking() {
        List<Booking> bookings = new ArrayList<Booking>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bookingPath)))
        {
            AirportLogger.info("считывание списка бронирований с файла");
            bookings=((List<Booking>)ois.readObject());
            AirportLogger.info("считано с файла бронирований:"+bookings.size());
        }
        catch(Exception e){
            AirportLogger.error("считывание списка бронирований с файла");
            e.printStackTrace();
        }
        return bookings;
    }
}
