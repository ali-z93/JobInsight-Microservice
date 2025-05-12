package com.ali.reviewms.review.reviewServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ali.reviewms.review.Review;
import com.ali.reviewms.review.ReviewRepository;
import com.ali.reviewms.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		// TODO Auto-generated method stub

		if (companyId != null && review != null) {
			review.setCompanyId(companyId);
			reviewRepository.save(review);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Review getReview(Long reviewId) {
		// TODO Auto-generated method stub
		
		
		return reviewRepository.findById(reviewId).orElse(null);
	}

	@Override
	public boolean updateReview(Long reviewId, Review updatedReview) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findById(reviewId).orElse(null);
		if (review != null) {
			review.setTitle(updatedReview.getTitle());
			review.setDescription(updatedReview.getDescription());
			review.setRating(updatedReview.getRating());
			review.setCompanyId(updatedReview.getCompanyId());
			reviewRepository.save(updatedReview);
			return true;

		} else {
			return false;
		}

	}

	@Override
	public boolean deleteReview(Long reviewId) {
		// TODO Auto-generated method stub
		Review review = reviewRepository.findById(reviewId).orElse(null);
		if (review != null) {
			reviewRepository.delete(review);
			return true;

		} else {
			return false;
		}

	}

}
