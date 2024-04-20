package com.app.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}/reviews/")
public class ReviewController
{
    private ReviewServices reviewServices;

    public ReviewController(ReviewServices reviewServices)
    {
        this.reviewServices = reviewServices;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findByCompanyId(@PathVariable Long companyId)
    {
        List<Review> reviews = reviewServices.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> findReviewById(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        Review review = reviewServices.findReviewById(companyId,reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        else
        {
            ResponseEntity<Review> reviewResponseEntity = (ResponseEntity<Review>) ResponseEntity.internalServerError();
            return reviewResponseEntity;
        }
    }

    @PostMapping
    public ResponseEntity<String> addreview(@PathVariable Long companyId, @RequestBody Review review)
    {
        boolean addStatus = reviewServices.addReview(companyId, review);
        if (addStatus)
            return ResponseEntity.ok("Review added to the company");
        else
            return new ResponseEntity<>("Error occurred during adding review", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review)
    {
        boolean updateStatus = reviewServices.updateReview(companyId, reviewId, review);
        if (updateStatus)
            return ResponseEntity.ok("Review updated successfully");
        else
            return new ResponseEntity<>("Error occurred during updating review", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,@PathVariable Long reviewId)
    {
        boolean deleteStatus = reviewServices.deleteReviewById(companyId,reviewId);
        if (deleteStatus)
            return ResponseEntity.ok("Review deleted successfully");
        else
            return new ResponseEntity<>("Error occurred during updating review", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}



