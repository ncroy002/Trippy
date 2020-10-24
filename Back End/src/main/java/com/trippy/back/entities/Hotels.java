package com.trippy.back.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import java.util.*;

@Entity
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String starRating;
    String address;
    int destinationId ;
    int pageNumber;
    int pageSize;
    String sortOrder;
    int adult;
    int children;
    String price;
    String photo;

    @ManyToMany(mappedBy = "hotels")
    private List<Account> accounts;

    public int getDestinationId(){return destinationId;}
    public void setDestinationId(int desId){this.destinationId = desId; }

    public int getPageNumber(){return pageNumber;}
    public void setPageNumber(int pageN){this.pageNumber = pageN;}

    public int getPageSize(){return pageSize;}
    public void setPageSize(int pageS){this.pageSize = pageS;}

    public String getSortOrder(){ return sortOrder; }
    public void setSortOrder(String order){this.sortOrder = order;}

    public int getAdult(){ return adult; }
    public void setAdult(int adult){this.adult = adult;}

    public int getChildren(){ return children; }
    public void setChildren(int child){this.children = child;}

    public String getPrice(){return price;}
    public void setPrice(String price){this.price = price;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getStarRating(){return starRating;}
    public void setStarRating(String starRating){this.starRating = starRating;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String getPhoto(){return photo;}
    public void setPhoto(String photo){this.photo = photo;}


    public Hotels(int destinationId,int pageNumber, int pageSize,String sortOrder,int adult,int children){
        this.destinationId = destinationId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
        this.adult = adult;
        this.children = children;
    }

    public Hotels(String name,String starRating,String address, String price,String photo){
        this.name = name;
        this.starRating = starRating;
        this.address = address;
        this.price = price;
        this.photo = photo;
    }

    public Hotels(){
    }

}
