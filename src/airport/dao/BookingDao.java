package airport.dao;

import airport.entity.Booking;

import java.util.List;

public class BookingDao extends AbstractAirportDao<Booking> implements IAirportDao<Booking> {
    public BookingDao(List<Booking> entities) {
        super(entities);
    }
}
