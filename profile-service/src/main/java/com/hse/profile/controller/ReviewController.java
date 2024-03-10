package com.hse.profile.controller;

import com.hse.profile.dto.ReviewCreateDto;
import com.hse.profile.dto.ReviewDto;
import com.hse.profile.entity.Profile;
import com.hse.profile.entity.Review;
import com.hse.profile.entity.ReviewGrade;
import com.hse.profile.mapper.ProfileMapper;
import com.hse.profile.service.ProfileService;
import com.hse.profile.service.ReviewService;
import com.hse.profile.util.JwtUtil;
import jakarta.ws.rs.NotAuthorizedException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
  private final ProfileMapper profileMapper;

  private final ReviewService reviewService;

  private final ProfileService profileService;

  private final JwtUtil jwtUtil;

  @PostMapping
  public ReviewDto createReview(
      @RequestBody ReviewCreateDto reviewCreateDto,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Long userId = Long.parseLong(userInfo.get("id").toString());

    Profile profile = profileService.getProfileById(reviewCreateDto.getProfileId());
    Review review = profileMapper.reviewCreateDtoToReview(reviewCreateDto);
    review.setProfile(profile);
    review.setUserId(userId);
    review.setReviewGrades(profile.getSkills().stream()
        .map(skillInfo -> {
          ReviewGrade reviewGrade = new ReviewGrade();
          reviewGrade.setSkillInfoId(skillInfo.getId());
          reviewGrade.setReview(review);
          return reviewGrade;
        })
        .collect(Collectors.toList()));

    return profileMapper.reviewToReviewDto(reviewService.addReview(review));
  }

  @PutMapping
  public ReviewDto updateReview(
      @RequestBody ReviewDto reviewDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Review review = profileMapper.reviewDtoToReview(reviewDto);

    if (review.getUserId() != Long.parseLong(userInfo.get("id").toString())) {
      throw new NotAuthorizedException("No access rights");
    }

    return profileMapper.reviewToReviewDto(reviewService.updateReview(review));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteReviewById(
      @PathVariable UUID id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Review review = reviewService.getReviewById(id);

    if (review.getUserId() == Long.parseLong(userInfo.get("id").toString())) {
      reviewService.deleteReviewById(id);
    } else {
      throw new NotAuthorizedException("No access rights");
    }
  }

  @GetMapping("/{id}")
  public ReviewDto getReviewById(@PathVariable UUID id) {
    return profileMapper.reviewToReviewDto(reviewService.getReviewById(id));
  }

  @GetMapping("/approve/{id}/{grade_id}/{is_approve}")
  public ReviewDto approveSkillByReviewIdAndReviewGradeId(
      @PathVariable UUID id, @PathVariable UUID grade_id, @PathVariable boolean is_approve,
      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
    jwtUtil.validateTokenAndCheckAccessRights(token, "ADMIN", "APPROVER");
    Map<String, Object> userInfo = jwtUtil.validateTokenAndExtractData(token);
    Long approverId = Long.parseLong(userInfo.get("id").toString());

    return profileMapper.reviewToReviewDto(
        reviewService.approveSkillByReviewIdAndReviewGradeId(id, grade_id, approverId, is_approve));
  }
}
