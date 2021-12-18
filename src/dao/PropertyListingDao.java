package dao;

import model.Property;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyListingDao {
    Map<String, User> userIdToUser;
    Map<String, Property> propertyIdToProperty;

    public  PropertyListingDao() {
        this.userIdToUser = new HashMap<>();
        this.propertyIdToProperty = new HashMap<>();
    }

    public User getUser(String userId) throws Exception {
        if(!userIdToUser.containsKey(userId)){
            throw new Exception("User does not exist.");
        }
        return userIdToUser.get(userId);
    }

    public void addUser(User user) {
        userIdToUser.put(user.getId(), user);
    }

    public Property getProperty(String propertyId) throws Exception {
        if(!propertyIdToProperty.containsKey(propertyId)) {
            throw new Exception("Property does not exist.");
        }
        return propertyIdToProperty.get(propertyId);
    }

    public void addProperty(Property property) {
        propertyIdToProperty.put(property.getId(), property);
    }

    public List<Property> getAllProperties() {
        return new ArrayList<>(propertyIdToProperty.values());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userIdToUser.values());
    }
}
