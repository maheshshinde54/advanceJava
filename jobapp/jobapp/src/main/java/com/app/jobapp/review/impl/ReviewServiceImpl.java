package com.app.jobapp.review.impl;

import com.app.jobapp.Company.Company;
import com.app.jobapp.Company.CompanyService;
import com.app.jobapp.review.Review;
import com.app.jobapp.review.ReviewRepository;
import com.app.jobapp.review.ReviewServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReviewServiceImpl implements ReviewServices
{
    private ReviewRepository reviewRepository;
    private CompanyService companyService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService)
    {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId)
    {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review)
    {
        Company company= companyService.findCompanyById(companyId);
        if(company!=null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review)
    {
        Company company= companyService.findCompanyById(companyId);
        if(company!=null)
        {
            review.setCompany(company);
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review findReviewById(Long companyId,Long reviewId)
    {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);

    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId)
    {Company company= companyService.findCompanyById(companyId);
        if(company!=null)
        {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else
            return false;
    }
}
