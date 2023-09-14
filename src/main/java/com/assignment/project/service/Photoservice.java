package com.assignment.project.service;

import com.assignment.project.model.photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class Photoservice {
    private static Map<String, photo> db = new HashMap<>(){{
        put("1", new photo("1", "test1.jpg"));
        put("2", new photo("2", "test2.jpg"));
    }};

    public static Collection<photo> get() {
        return db.values();
    }

    public static photo get(String id) {
        return db.get(id);
    }

    public static photo remove(String id) {
        return db.remove(id);
    }

    public photo save(String filename, byte[] data, String contentType) {
        photo obj=new photo();
        obj.setFilename(filename);
        obj.setContenttype(contentType);
        obj.setId(UUID.randomUUID().toString());
        obj.setData(data);
        db.put(obj.getId(),obj);
        return obj;
    }
}
