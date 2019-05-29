package com.behin.toursearchdemo.pictureUpload;


//import org.apache.log4j.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//public class ValidationController {
//    private Logger logger;
//
//    ValidationController(){ logger = Logger.getLogger(this.getClass());}
//
//    boolean phoneNumberChecker(String phone){
//        logger.info("phoneNumberChecker"+" phone = "+phone);
//        if(phone.length()!=11 || ! phone.startsWith("09"))
//            return false;
//
//        for (int i = 0 ; i<phone.length() ; i++)
//            if(!Character.isDigit(phone.charAt(i)))
//                return false;
//        return true;
//    }
//
//    boolean cellPhoneChecker(String cellPhone){
//        logger.info("cellPhoneChecker"+" cellphone = "+cellPhone);
//        if(cellPhone.length() != 11 || !cellPhone.startsWith("021"))
//            return false;
//        for (int i = 0 ; i<cellPhone.length() ; i++)
//            if(!Character.isDigit(cellPhone.charAt(i)))
//                return false;
//        return true;
//    }
//
//    boolean emailChecker(String email){
//        logger.info("emailChecker"+" email = "+email);
//        if(! email.contains(".")||!email.contains("@") || email.isEmpty() || email.split("@").length>2 || email.lastIndexOf('.')<email.lastIndexOf('@')
//                ||email.lastIndexOf("@")<=0){
//            return false;
//        }
//        return true;
//    }
//
//    boolean imageTypeChecker(String type){
//        logger.info("imageTypeChecker"+" type = "+type);
//        List<String> validTypes = Arrays.asList("PNG","JPG","JPEG","EXIF","TIFF","TIF","BMP","WEBP");
//        for (String validType:validTypes){
//            if(Objects.equals(type.toUpperCase(), validType))
//                return true;
//        }
//        return false;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
