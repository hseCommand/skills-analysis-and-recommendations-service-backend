package com.hse.profile.service;

import com.hse.profile.entity.Review;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
  Review updateReview(Review review);

  Review getReviewById(UUID reviewId);

  Review addReview(Review review);

  void deleteReviewById(UUID id);

  List<Review> getReviewsByFilter(ReviewsFilter reviewsFiler);

  List<Review> getReviewsByUserId(Long userId);

  Review approveSkillByReviewIdAndReviewGradeId(UUID reviewId, UUID reviewGradeId,
      Long approverId, boolean isApprove);

}
