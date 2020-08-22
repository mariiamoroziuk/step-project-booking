package airport.entity;

import java.io.Serializable;

public class Booking extends AbstractEntity implements Serializable {
    private Long flightId;
    private Long userId;

    public Booking(Long flightId, Long userId){
        setFlightId(flightId);
        setUserId(userId);
    }

    public Booking(Long id, Long flightId, Long userId){
        this(flightId, userId);
        setId(id);
    }

    public Long getFlightId(){
        return this.flightId;
    }
    private void setFlightId(Long flightId) {
        this.flightId = flightId;
    }
    public Long getUserId(){ return this.userId; }
    private void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + this.getId() +
                ", flightId=" + flightId +
                ", userId=" + userId +
                '}';
    }
}
