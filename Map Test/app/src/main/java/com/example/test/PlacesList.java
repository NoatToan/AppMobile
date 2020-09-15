package com.example.test;

public class PlacesList {
    private String placeName, placeLink, placeLocation;
    private int placeImage;
    public PlacesList() {}
    public PlacesList(String placeName, String placeLink, int placeImage, String placeLocation) {
        this.placeName = placeName;
        this.placeLink = placeLink;
        this.placeImage = placeImage;
        this.placeLocation = placeLocation;
    }
    public String getPlaceName() { return placeName;
    }
    public String getPlaceLink() { return placeLink;
    }
    public String getPlaceLocation() { return placeLocation;
    }
    public int getPlaceImage() { return placeImage; }
    public void setPlaceImage(int placeImage) { this.placeImage = placeImage; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }
    public void setPlaceLink(String placeLink) { this.placeLink = placeLink; }
    public void setPlaceLocation(String placeLocation) { this.placeLocation = placeLocation;
    }
}
