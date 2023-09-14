package com.assignment.project.web;

import com.assignment.project.service.Photoservice;
import com.assignment.project.model.photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.Collection;

@RestController //when the application boots up first thing the browser see is this tag
public class Photocontroller {

    private final Photoservice photoservice;

    public Photocontroller(Photoservice photoservice) {
        this.photoservice = photoservice;
    }

    @GetMapping("/photos") // this path will give all the collection of photos present in the collection
    public Collection<photo> get(){
     return photoservice.get();
    }

    @GetMapping("/photos/{id}") // this path will give the photo according to id
    public photo get(@PathVariable String id){
        photo obj = photoservice.get(id);
        if(obj == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return obj;
    }

    @DeleteMapping("/photos/{id}") // this path will delete the photo which can be verified by the first path
    public void delete(@PathVariable String id){
        photo obj = photoservice.remove(id);
        if(obj == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        //code to delete photo from console
        //(async function deletePhoto(id) {
        //          await fetch("http://localhost:8080/photos/" + id, {
        //                    method: "DELETE"
        //          })
        //})("2")
    }

//    @PostMapping("/photos")
//    public photo create(@RequestBody @Valid photo obj){  /// this path will post an image from the console maunally
//        obj.setId(UUID.randomUUID().toString());
//        db.put(obj.getId(),obj);
//        return obj;
//        //code to post image
//        //(async function createPhoto() {
//        //          let photo = {"filename": "hello.jpg"};
//        //
//        //          await fetch("http://localhost:8080/photos", {
//        //                    method: "POST",
//        //                    headers: {
//        //                              "Accept": "application/json",
//        //                              "Content-Type": "application/json"
//        //                    },
//        //                    body: JSON.stringify(photo)
//        //                    })
//        //                    .then(result => result.text())
//        //                    .then(text => alert(text));
//        //})();
//    }

    @PostMapping("/photos") // this path will redirect the code to the html page where we upload the picture that we can see
    public photo create(@RequestPart("data") MultipartFile file) throws IOException {
        photo obj= photoservice.save(file.getOriginalFilename(), file.getBytes(),file.getContentType());
        System.out.println(file.getBytes().toString());
        return obj;

    }

}

