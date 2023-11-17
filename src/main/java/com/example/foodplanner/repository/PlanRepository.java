package com.example.foodplanner.repository;

import com.example.foodplanner.model.entity.Plan;
import com.example.foodplanner.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long>{
	
	@Query("select p from Plan p where p.user = ?1 AND p.startDate <= ?2 AND p.endDate >= ?2")
	Plan findByUserIdAndStartDateBeforeAndEndDateAfter(User user, LocalDate today);

	List<Plan> findAllByUserId(Long userId);

	List<Plan> findAllByUserIdAndEndDateGreaterThanEqual(Long userId, LocalDate date);

	List<Plan> findAllByUserIdAndEndDateLessThan(Long userId, LocalDate date);

}
