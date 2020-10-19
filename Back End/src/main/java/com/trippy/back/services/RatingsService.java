package com.trippy.back.services;

import com.trippy.back.repos.EventRatingsRepo;
import com.trippy.back.repos.HotelRatingsRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingsService {
    @Autowired
    EventRatingsRepo eventsRepo;

    @Autowired
    HotelRatingsRepo hotelsRepo;

}
