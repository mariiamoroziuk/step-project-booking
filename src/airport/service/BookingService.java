package airport.service;

import airport.dao.IAirportDao;
import airport.entity.Booking;
import airport.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final IAirportDao<Booking> bookingDao;

    public BookingService(IAirportDao<Booking> bookingDao) {
        this.bookingDao = bookingDao;
    }

    public List<Booking> getAllBookings() { return this.bookingDao.findAll(); }
    public boolean deleteBookingById(Long id) { return this.bookingDao.deleteById(id); }
    public void saveBooking(Booking booking) {
        this.bookingDao.save(booking);
    }
    public List<Booking> getBookingsByUserId (Long id){
        return this.bookingDao.findAll()
                .stream()
                .filter(b-> b.getUserId().equals(id))
                .collect(Collectors.toList());
    }
    public int getCountBookingByFlightId(long id) {
        return (int) this.bookingDao.findAll()
                .stream()
                .filter(b-> b.getFlightId().equals(id))
                .count();
    }
    public List<Flight> getFlightsEmptySeats(List<Flight> flights, int userCount) {
        return flights
                .stream()
                .filter(f-> (f.getTotalSeats() - this.getCountBookingByFlightId(f.getId())) >= userCount)
                .collect(Collectors.toList());
    }
}
