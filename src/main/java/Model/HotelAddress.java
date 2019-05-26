/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import lombok.Data;

/**
 *
 * @author bamika
 */

@Data
public class HotelAddress {


private String country;

private String state;

private String city;

private String street;

private String no;

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getStreet() {
return street;
}

public void setStreet(String street) {
this.street = street;
}

public String getNo() {
return no;
}

public void setNo(String no) {
this.no = no;
}

}