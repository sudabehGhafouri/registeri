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
public class response {
    private String result;
    
    public String getResult() {
        return result;
    }

    public void setHotel_name(String s_result) {
        this.result = s_result;
    }
}
