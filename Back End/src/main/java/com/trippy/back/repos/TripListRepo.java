package com.trippy.back.repos;

import com.trippy.back.entities.TripList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripListRepo extends JpaRepository<TripList,Long> {
    @Query(value = "select tripList from TripList tripList where tripList.name = ?1 and tripList.account.id = ?2")
    TripList findTripListByNameandAccount(String name, long userID);

    @Query(value = "select tripList.name from TripList tripList where tripList.account.id = ?1")
    List<String> findUserTripLists(long userID);
}
