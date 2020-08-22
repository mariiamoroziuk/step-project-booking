package airport.service;

import airport.dao.IAirportDao;
import airport.entity.Flight;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final IAirportDao<Flight> flightDao;

    public FlightService(IAirportDao<Flight> flightDao){
        this.flightDao = flightDao;
    }

    public List<Flight> getAllFlights(){
        return this.flightDao.findAll();
    }
    public Flight getFlightById(long id){
        return this.flightDao.getOne(id);
    }
    public List<Flight> getFlightByDepartureTimeAndPlace(LocalDateTime departureTime, int duration, String departurePlace){
        return this.flightDao.findAll()
                .stream()
                .filter(f -> f.getDepartureTime().isBefore(departureTime.plusHours(duration)) && (departureTime.isBefore(f.getDepartureTime())) && (f.getDeparturePlace().equals(departurePlace)))
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .collect(Collectors.toList());
    }
    public List<Flight> getFlightByDepartureArrival(LocalDateTime departureTime, int duration, String departurePlace, String arrivalPlace){
        return getFlightByDepartureTimeAndPlace(departureTime, duration, departurePlace)
                .stream()
                .filter(f -> f.getArrivalPlace().equals(arrivalPlace))
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .collect(Collectors.toList());
    }
}
