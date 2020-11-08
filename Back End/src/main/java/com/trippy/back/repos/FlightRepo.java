package com.trippy.back.repos;

import com.trippy.back.entities.FoundFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends JpaRepository<FoundFlight,Long> {

}
