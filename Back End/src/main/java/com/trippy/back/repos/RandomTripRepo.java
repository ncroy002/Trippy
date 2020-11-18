package com.trippy.back.repos;
import com.trippy.back.entities.TripList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomTripRepo extends JpaRepository<TripList,Long>{
}
