package com.trippy.back.services;

import com.trippy.back.entities.Forums;
import com.trippy.back.repos.ForumsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ForumsService {
    @Autowired
    ForumsRepo forumsrepo;

    public List<Forums> findAllForums(){
        return forumsrepo.findAll();
    }

    /*
    public
     */
}
