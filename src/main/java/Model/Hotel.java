/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * @author bamika
 */

import lombok.Data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author bamika
 */

@Data
public class Hotel {

    private String hotelId;
    private String hotelName;
    private int hotelRank;
    private int hotelPhon;
    private String hotelDescription;
    private HotelAddress hotelAddress;
    private List<String> imagePath = null;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRank() {
        return hotelRank;
    }

    public void setHotelRank(int hotelRank) {
        this.hotelRank = hotelRank;
    }

    public int getHotelPhon() {
        return hotelPhon;
    }

    public void setHotelPhon(int hotelPhon) {
        this.hotelPhon = hotelPhon;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public HotelAddress getHotelAddress() { return hotelAddress; }

    public void setHotelAddress(HotelAddress hotelAddress) {this.hotelAddress = hotelAddress; }


    public List<String> getImagePath() { return imagePath; }

    public void setImagePath(List<String> imagePath) {
        this.imagePath = imagePath;
    }

}
