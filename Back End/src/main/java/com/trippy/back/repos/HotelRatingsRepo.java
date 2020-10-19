package com.trippy.back.repos;

import com.trippy.back.entities.HotelRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRatingsRepo extends JpaRepository<HotelRating, Long> {


}
