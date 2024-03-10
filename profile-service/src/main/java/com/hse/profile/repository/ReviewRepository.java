package com.hse.profile.repository;

import com.hse.profile.entity.Review;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
  List<Review> getReviewsByUserId(Long userId);
}
