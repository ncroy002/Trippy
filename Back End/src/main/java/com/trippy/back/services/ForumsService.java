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
    ForumsRepo ForumsRepo;

    public List<Forums> findAllForums(){
        return ForumsRepo.findAll();
    }

    public ResponseEntity addForums(Forums forums){
        ForumsRepo.save(forums);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void updateForums(Forums forums){
        try{
            List<Forums> list = findAllForums();
            if(list.contains(forums)){
                Forums forumsItems = list.get(list.indexOf(forums));

                //Forum User
                if(forumsItems.getForumUser().equals(forums.getForumUser())){
                    forumsItems.setForumUser(forumsItems.getForumUser());
                }
                else{
                    forumsItems.setForumUser(forums.getForumUser());
                }

                //Forum Title
                if(forumsItems.getForumTitle().equals(forums.getForumTitle())){
                    forumsItems.setForumTitle(forumsItems.getForumTitle());
                }
                else{
                    forumsItems.setForumTitle(forums.getForumTitle());
                }

                //Forum Post Date
                if(forumsItems.getForumPostDate().equals(forums.getForumPostDate())){
                    forumsItems.setForumPostDate(forumsItems.getForumPostDate());
                }
                else{
                    forumsItems.setForumPostDate(forums.getForumPostDate());
                }

                //Forum Post
                if(forumsItems.getForumPost().equals(forums.getForumPost())){
                    forumsItems.setForumPost(forumsItems.getForumPost());
                }
                else{
                    forumsItems.setForumPost(forums.getForumPost());
                }
                BackApplication.LOGGER.info("Updating the forums ["+forumsItems+"]");
                ForumsRepo.save(forumsItems);
            }
        }
        catch(Exception e){
            BackApplication.LOGGER.log(Level.SEVERE, "Failed to update the forums ["+forums+"]", e);
        }
        throw new RuntimeException("Forums ["+forums+"] does not exist in the database.");
    }
}
