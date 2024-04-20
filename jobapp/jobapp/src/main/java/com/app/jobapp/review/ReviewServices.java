package com.app.jobapp.review;

import java.util.List;

public interface ReviewServices
{

    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    Review findReviewById(Long companyId, Long reviewId);

    boolean deleteReviewById(Long companyId, Long reviewId);


}
