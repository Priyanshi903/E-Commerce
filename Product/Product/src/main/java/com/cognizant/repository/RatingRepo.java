package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, String> {

}
