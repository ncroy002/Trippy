package com.trippy.back.repos;

import com.trippy.back.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepo extends JpaRepository<Interest,Long> {

}
