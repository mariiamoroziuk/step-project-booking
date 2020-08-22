package airport.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight extends AbstractEntity implements Serializable {
    private String departurePlace;
    private String arrivalPlace;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int totalSeats;

    public Flight(Long id, String departurePlace, String arrivalPlace, LocalDateTime departureTime, LocalDateTime arrivalTime, int totalSeats){
        setId(id);
        setDeparturePlace(departurePlace);
        setArrivalPlace(arrivalPlace);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setTotalSeats(totalSeats);
    }

    public String getDeparturePlace(){
        return this.departurePlace;
    }
    private void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }
    public String getArrivalPlace(){
        return this.arrivalPlace;
    }
    private void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }
    public LocalDateTime getDepartureTime(){
        return this.departureTime;
    }
    private void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDateTime getArrivalTime(){
        return this.arrivalTime;
    }
    private void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getTotalSeats(){
        return this.totalSeats;
    }
    private void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "\n номер " + getId()+ "\t" +
                "отправление: " + departurePlace  + "\t" +
                departureTime.format(DateTimeFormatter.ofPattern("HH:mm dd-MM-uuuu")) +"\t"+
                "прибытие: "+arrivalPlace + "\t" +
                arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm dd-MM-uuuu"));
    }
}
