package com.trippy.back.repos;

import com.trippy.back.entities.FoundFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<FoundFlight,Long> {

}
