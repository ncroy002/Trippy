package com.trippy.back.repos;

import com.trippy.back.entities.EventRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRatingsRepo extends JpaRepository<EventRating, Long> {


}
