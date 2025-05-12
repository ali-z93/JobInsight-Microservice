package com.ali.reviewms.review;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);

	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
		return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
		boolean isReviewSaved = reviewService.addReview(companyId, review);
		if (isReviewSaved) {
			return new ResponseEntity<>("review ADDED successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("review NOT SAVED", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
			@RequestBody Review review) {
		boolean isReviewUpdated = reviewService.updateReview(reviewId, review);
		if (isReviewUpdated) {
			return new ResponseEntity<>("review UPDATED successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("review NOT UPDATED", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview( @PathVariable Long reviewId) {

		boolean isReviewUpdated = reviewService.deleteReview(reviewId);
		if (isReviewUpdated) {
			return new ResponseEntity<>("review DELETED successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("review NOT DELETED", HttpStatus.NOT_FOUND);
		}
	}

}
