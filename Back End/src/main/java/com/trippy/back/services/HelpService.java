package com.trippy.back.services;

import com.trippy.back.entities.Help;
import com.trippy.back.repos.HelpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


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



}

