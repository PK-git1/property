package model;

import constants.ListingType;
import constants.PropertyStatus;

import java.util.UUID;

public class Property {
    private String id;
    private String name;
    private String location;
    private ListingType listingType;
    private Integer roomType;
    private Double price;
    private Double size;
    private PropertyStatus status;

    public Property(String name, String location, ListingType listingType, Integer roomType, Double price, Double size) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.listingType = listingType;
        this.roomType = roomType;
        this.price = price;
        this.size = size;
        this.status = PropertyStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", listingType=" + listingType +
                ", roomType=" + roomType +
                ", price=" + price +
                ", size=" + size +
                ", status=" + status +
                '}';
    }
}
