package com.hse.profile.service;

import com.hse.profile.entity.Profile;
import com.hse.profile.entity.Review;
import com.hse.profile.entity.ReviewGrade;
import com.hse.profile.exception.NoSuchProfileException;
import com.hse.profile.exception.NoSuchReviewException;
import com.hse.profile.exception.NoSuchReviewGradeException;
import com.hse.profile.repository.ReviewRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public Review updateReview(Review review) {
    var reviewOptional = reviewRepository.findById(review.getId());
    if (reviewOptional.isPresent()) {
      review.setCreatedAt(reviewOptional.get().getCreatedAt());
      return reviewRepository.save(review);
    } else {
      throw new NoSuchReviewException();
    }
  }

  @Override
  public Review getReviewById(UUID reviewId) {
    return reviewRepository.findById(reviewId).orElseThrow(NoSuchReviewException::new);
  }

  @Override
  public Review addReview(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public void deleteReviewById(UUID id) {
    reviewRepository.deleteById(id);
  }

  @Override
  public List<Review> getReviewsByFilter(ReviewsFilter reviewsFiler) {
    return null;
  }

  @Override
  public List<Review> getReviewsByUserId(Long userId) {
    return reviewRepository.getReviewsByUserId(userId);
  }

  @Override
  public Review approveSkillByReviewIdAndReviewGradeId(UUID reviewId, UUID reviewGradeId,
      Long approverId, boolean isApprove) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(NoSuchReviewException::new);
    review.setApproverId(approverId);
    ReviewGrade reviewGrade = review.getReviewGrades().stream().filter(
        grade -> grade.getId() == reviewGradeId).findFirst().orElseThrow(NoSuchReviewGradeException::new);
    reviewGrade.setApprove(isApprove);
    reviewRepository.save(review);
    return review;
  }
}
