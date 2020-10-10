package com.trippy.back.repos;

import com.trippy.back.entities.FoundTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<FoundTrip,Long> {

}
