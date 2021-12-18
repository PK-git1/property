package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private List<Property> listedProperties;
    private List<Property> shortlistedProperties;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.listedProperties = new ArrayList<>();
        this.shortlistedProperties = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Property> getListedProperties() {
        return listedProperties;
    }

    public List<Property> getShortlistedProperties() {
        return shortlistedProperties;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", listedProperties=" + listedProperties +
                ", shortlistedProperties=" + shortlistedProperties +
                '}';
    }
}
