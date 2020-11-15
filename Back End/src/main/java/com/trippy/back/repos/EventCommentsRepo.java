package com.trippy.back.repos;

import com.trippy.back.entities.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentsRepo extends JpaRepository<EventComment, Integer> {
}
