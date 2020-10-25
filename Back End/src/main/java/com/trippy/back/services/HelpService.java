package com.trippy.back.services;

import com.trippy.back.BackApplication;
import com.trippy.back.entities.Help;
import com.trippy.back.repos.HelpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Level;

@Service
public class HelpService {
    @Autowired
    HelpRepo HelpRepo;

    public List<Help> getAllHelps(){
        return HelpRepo.findAll();
    }

    public void deleteHelp(Long id){
        if(HelpRepo.existsById(id)){
            HelpRepo.deleteById(id);
        }
    }

    public ResponseEntity addHelp(Help help){
        //maybe check for existing message
            HelpRepo.save(help);
            return new ResponseEntity<>(HttpStatus.OK);
    }

/*
    public void updateHelp(Long help){
        try {
            List<Help> list = getAllHelps();
            if (list.contains(help)) {
                Help helpItems = list.get(list.indexOf(help));
                helpItems.setQuestion(helpItems.getQuestion().equals(help.getQuestion()) ?  helpItems.getQuestion(): help.getQuestion());
                helpItems.setName(helpItems.getName().equals(help.getName()) ?  helpItems.getName(): help.getName());
                helpItems.setEmail(helpItems.getEmail().equals(help.getEmail()) ?  helpItems.getEmail(): help.getEmail());
                helpItems.setCompleted(helpItems.getCompleted().equals(help.getCompleted()) ?  helpItems.getCompleted(): help.getCompleted());

                BackApplication.LOGGER.info("Updating help ["+helpItems+"]");
                HelpRepo.save(helpItems);
                return;
            }
        } catch(Exception e){
            BackApplication.LOGGER.log(Level.SEVERE, "Failed to update Help ["+help+"]", e);
        }
        throw new RuntimeException("Help ["+help+"] does not exist in DB.");
    }
*/
}

