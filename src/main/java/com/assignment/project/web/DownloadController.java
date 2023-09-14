package com.assignment.project.web;

import com.assignment.project.service.Photoservice;
import com.assignment.project.model.photo;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController/// tags that represent certain functions that are signals to the code for various functions
public class DownloadController {

    private final Photoservice photoservice; //object of Photoservice class that is used below to access the methods in that class

    public DownloadController(Photoservice photoservice) {
        this.photoservice = photoservice;
    }


    @GetMapping("/photos/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){
        photo obj = photoservice.get(id);
        if(obj == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = obj.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(obj.getContenttype()));
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(obj.getFilename())
                .build();
        headers.setContentDisposition(build);
        return new ResponseEntity<>(data,headers, HttpStatus.OK);
    }

}
