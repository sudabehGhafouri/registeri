package com.behin.toursearchdemo.pictureUpload;

import org.apache.log4j.Logger;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("api/file")
public class UploadController {
//    private  static String UPLOAD_DIR="uploads";
//    @RequestMapping(value="upload",method=RequestMethod.POST)
//    public  String upload (@RequestParam("file") MultipartFile file, HttpServletRequest request){
//        try {
//            String fileName = file.getOriginalFilename();
//            String path = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName;
////            String path =UPLOAD_DIR ;
//            if (path != null) // null will be returned if the path has no parent
//                Files.createDirectories(Paths.get(path));
//            saveFile(file.getInputStream(),path);
//            return fileName;
//        }catch (Exception e){
//            return e.getMessage();
//        }
//    }
//    private  void  saveFile(InputStream inputStream, String path){
//        try {
//            OutputStream outputStream=new FileOutputStream(new File(path));
//            int read=0;
//            byte[] bytes=new byte[1024];
//            while ((read=inputStream.read(bytes))!=-1){
//                outputStream.write(bytes,0,read);
//                outputStream.flush();
//                outputStream.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//   ==================================================================================================
    private Logger logger;

    public UploadController(){
        this.logger = Logger.getLogger(this.getClass());
    }
//
@RequestMapping(value="upload",method=RequestMethod.POST)
    public NewPicture singleFileUpload(@RequestParam("file") MultipartFile file){
        NewPicture newPicture = new NewPicture();
//        ValidationController validationController = new ValidationController();
        if (file.isEmpty()){
            newPicture.setError(true);
            return newPicture;
        }
        try {
            int index = file.getOriginalFilename().lastIndexOf(".");
            int length = file.getOriginalFilename().length();
            String type = file.getOriginalFilename().substring(index+1,length);
//            if(!validationController.imageTypeChecker(type)){
//                newPicture.setError(true);
//                return newPicture;
//            }
            String fileName = "IMG_" +
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date()) ;
            Random random  = new Random();
            String randomChar = random.nextLong()+"";

            byte[] bytes = file.getBytes();
            Path pathUrl = Paths.get(fileName+randomChar+file.getOriginalFilename());

            String UPLOAD_FOLDER = "/Users/bamika/Desktop/TourRecommenderSystem/src/main/resources/static/img/";
            Path path = Paths.get(UPLOAD_FOLDER +file.getOriginalFilename());
            if (path != null) // null will be returned if the path has no parent
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            Files.write(path,bytes);
            String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/secure/download/image/").path(pathUrl.toString()).toUriString();
            newPicture.setName(fileDownloadUrl);
            newPicture.setError(false);
            return newPicture;

        }catch (IOException e){
            logger.error("singleFileUpload "+e);
            newPicture.setError(true);
            return newPicture;
        }
    }

    @RequestMapping(method = RequestMethod.GET , value = "/secure/download/image/{fileName}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("fileName") String fileName) throws IOException{
        File file = new File("/uploads/"+fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+file.getName())
                .contentLength(file.length())
                .body(resource);

    }

}
