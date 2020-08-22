package airport.dao;

import airport.entity.Flight;

import java.util.List;

public class FlightDao extends AbstractAirportDao<Flight> implements IAirportDao<Flight> {

    public FlightDao(List<Flight> entities) {
        super(entities);
    }
}

