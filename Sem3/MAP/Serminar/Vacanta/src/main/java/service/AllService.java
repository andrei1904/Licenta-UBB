package service;

import domain.Hotel;
import domain.Location;

import java.util.List;
import java.util.stream.Collectors;

public class AllService {
    final HotelService hotelService;
    final LocationService locationService;

    public AllService(HotelService hotelService, LocationService locationService) {
        this.hotelService = hotelService;
        this.locationService = locationService;
    }

    public List<Hotel> getAllHotels() {
        return hotelService.getAll();
    }

    public List<Location> getAllLocations() {
        return locationService.getAll();
    }

    public List<Hotel> getHotelsFromLocation(double id) {
        return hotelService.getAll()
                .stream()
                .filter(x -> x.getLocationId() == id)
                .collect(Collectors.toList());
    }
}
