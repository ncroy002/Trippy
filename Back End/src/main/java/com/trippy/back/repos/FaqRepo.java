package com.trippy.back.repos;
import com.trippy.back.entities.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepo extends JpaRepository <Faq, Long> {
 //   Faq findById(Long id);

}
