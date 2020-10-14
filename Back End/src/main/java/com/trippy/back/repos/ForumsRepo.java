package com.trippy.back.repos;

import com.trippy.back.entities.Forums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumsRepo extends JpaRepository <Forums, Integer> {
}
