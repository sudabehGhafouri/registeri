/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author bamika
 */
import lombok.Data;

/**
 *
 * @author bamika
 */
@Data
public class Hotel {
    private int    hoteh_id;
    private String hotel_name;
    private int    hotel_rank;
    private int    hotel_phon;
    private String hotel_description;
    private String hotel_address;

    public int getHoteh_id() {
        return hoteh_id;
    }

    public void setHoteh_id(int hoteh_id) {
        this.hoteh_id = hoteh_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public int getHotel_rank() {
        return hotel_rank;
    }

    public void setHotel_rank(int hotel_rank) {
        this.hotel_rank = hotel_rank;
    }

    public int getHotel_phon() {
        return hotel_phon;
    }

    public void setHotel_phon(int hotel_phon) {
        this.hotel_phon = hotel_phon;
    }

    public String getHotel_description() {
        return hotel_description;
    }

    public void setHotel_description(String hotel_description) {
        this.hotel_description = hotel_description;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

}
