package airport.controller;

import airport.entity.Flight;
import airport.logger.AirportLogger;
import airport.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        AirportLogger.info("создание обьекта FlightController");
        this.flightService = flightService;
    }

    public List<Flight> getAllFlights() {
        AirportLogger.info("получения списка всех рейсов");
        return this.flightService.getAllFlights();
    }
    public Flight getFlightById(long id) {
        AirportLogger.info("получение рейса по айди");
        return this.flightService.getFlightById(id);
    }
    public List<Flight> getFlightByDepartureTimeAndPlace(LocalDateTime departureTime, int duration, String departurePlace){
        AirportLogger.info("получение рейсов по времени, месту вылета и периода поиска");
        return this.flightService.getFlightByDepartureTimeAndPlace(departureTime, duration, departurePlace);
    }
    public List<Flight> getFlightByDepartureArrival(LocalDateTime departureTime, int duration, String departurePlace, String arrivalPlace){
        AirportLogger.info("получение рейсов по времени, месту вылета, месту прилета и периода поиска");
        return this.flightService.getFlightByDepartureArrival(departureTime, duration, departurePlace, arrivalPlace);
    }
}
