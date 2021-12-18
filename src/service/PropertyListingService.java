package service;

import constants.ListingType;
import constants.PropertyStatus;
import constants.SortBy;
import dao.PropertyListingDao;
import model.Property;
import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class PropertyListingService {
    private PropertyListingDao propertyListingDao;
    private PriceParserService priceParserService;

    private static Map<String, Integer> ROOM_TYPE = new HashMap<>();

    public PropertyListingService(){
        this.propertyListingDao = new PropertyListingDao();
        this.priceParserService = new PriceParserService();
        ROOM_TYPE.put("1BHK", 1);
        ROOM_TYPE.put("2BHK", 2);
        ROOM_TYPE.put("3BHK", 3);
    }

    public void registerUser(String name) {
        User user = new User(name);
        propertyListingDao.addUser(user);
        System.out.println("User registered successfully");
        System.out.println(user.toString());
    }

    public void registerProperty(String userId, String propertyName, String location, String price, String listingType, String size, String roomType) {
        try {
            ListingType listingTypeEnum = ListingType.valueOf(listingType);
            if(listingTypeEnum==null){
                System.out.println("Invalid listing type");
                return;
            }
            Integer roomTypeId = ROOM_TYPE.get(roomType);
            if(roomTypeId==null){
                System.out.println("Invalid room type");
                return;
            }
            Double priceValue = priceParserService.parsePrice(price);
            Double sizeValue = Double.parseDouble(size);
            Property property = new Property(propertyName, location, listingTypeEnum,roomTypeId, priceValue, sizeValue);
            propertyListingDao.addProperty(property);
            User user = propertyListingDao.getUser(userId);
            user.getListedProperties().add(property);
            System.out.println("Property registered successfully");
            System.out.println(property.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchProperty(String location, String listingType, String roomType, String sortBy) {
        List<Property> propertyList = propertyListingDao.getAllProperties();
        if(propertyList.isEmpty()) {
            System.out.println("No properties available.");
        }

        List<Property> availableProperties = propertyList.stream().filter(property -> {
            if(PropertyStatus.AVAILABLE.equals(property.getStatus())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        List<Property> filteredOnLocation = availableProperties.stream().filter(property -> {
            if(location==null) return true;
            if(location.toLowerCase().equals(property.getLocation().toLowerCase())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        List<Property> filteredOnListingType = filteredOnLocation.stream().filter(property -> {
            if(listingType==null) return true;
            if(ListingType.valueOf(listingType).equals(property.getListingType())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        List<Property> filteredOnRoomType = filteredOnListingType.stream().filter(property -> {
            if(roomType==null) return true;
            if(ROOM_TYPE.getOrDefault(roomType,-1).equals(property.getRoomType())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if(SortBy.PRICE.equals(SortBy.valueOf(sortBy))) {
            filteredOnRoomType.sort(new Comparator<Property>() {
                @Override
                public int compare(Property p1, Property p2) {
                    return p1.getPrice() > p2.getPrice() ? 1 : -1;
                }
            });
        }


        if(SortBy.SIZE.equals(SortBy.valueOf(sortBy))) {
            filteredOnRoomType.sort(new Comparator<Property>() {
                @Override
                public int compare(Property p1, Property p2) {
                    return p1.getSize() > p2.getSize() ? 1 : -1;
                }
            });
        }

        if(filteredOnRoomType.isEmpty()) {
            System.out.println("No properties found");
            return;
        }
        for(Property property: filteredOnRoomType) {
            System.out.println(property.toString());
        }
    }

    public void shortListProperty(String userId, String propertyId) {
        try {
            User user = propertyListingDao.getUser(userId);
            Property property = propertyListingDao.getProperty(propertyId);
            user.getShortlistedProperties().add(property);
            System.out.println("Property shortlisted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewShortListedProperty(String userId) {
        try{
            User user = propertyListingDao.getUser(userId);
            List<Property> shortlistedProperties = user.getShortlistedProperties();
            if(shortlistedProperties.isEmpty()){
                System.out.println("No properties shortlisted.");
                return;
            }
            for(Property property: shortlistedProperties) {
                System.out.println(property.toString());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void markPropertySold(String propertyId) {
        try {
            Property property = propertyListingDao.getProperty(propertyId);
            property.setStatus(PropertyStatus.SOLD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllUsers() {
        List<User> users = propertyListingDao.getAllUsers();
        for(User user: users) {
            System.out.println(user.toString());
        }
    }
}
