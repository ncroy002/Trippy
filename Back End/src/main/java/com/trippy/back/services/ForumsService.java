package com.trippy.back.services;

import com.trippy.back.BackApplication;
import com.trippy.back.entities.Forums;
import com.trippy.back.repos.ForumsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;

@Service
public class ForumsService {
    @Autowired
    ForumsRepo forumsrepo;

    public List<Forums> findAllForums(){
        return forumsrepo.findAll();
    }

    public ResponseEntity addForums(Forums forums){
        forumsrepo.save(forums);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    public void updateForums(Forums forums){
        try{
            List<Forums> list = findAllForums();
            if(list.contains(forums)){
                Forums forumsItems = list.get(list.indexOf(forums));

                if(forumsItems.getPost().equals(forums.getPost())){
                    forumsItems.setPost(forumsItems.getPost());
                }
                else{
                    forums.getPost();
                }
                BackApplication.LOGGER.info("Updating the forums ["+forumsItems+"]");
                forumsrepo.save(forumsItems);
            }
        }
        catch(Exception e){
            BackApplication.LOGGER.log(Level.SEVERE, "Failed to update the forums ["+forums+"]", e);
        }
        throw new RuntimeException("Forums ["+forums+"] does not exist in the database.");
    }
    */
}
