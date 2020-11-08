package com.trippy.back.repos;

import com.trippy.back.entities.TripList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TripListRepo extends JpaRepository<TripList,Long> {
    @Query(value = "select tripList from TripList tripList where tripList.name = ?1 and tripList.account.id = ?2")
    TripList findTripListByNameandAccount(String name, long userID);
}
