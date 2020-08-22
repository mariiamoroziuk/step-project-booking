package airport.dao;

import airport.entity.User;

import java.util.List;

public class UserDao extends AbstractAirportDao<User> implements IAirportDao<User> {

    public UserDao(List<User> entities) {
        super(entities);
    }
}
