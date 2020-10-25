package com.trippy.back.repos;
import com.trippy.back.entities.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepo extends JpaRepository<Hotels,Long> {
}
