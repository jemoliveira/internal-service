package com.samsung.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.samsung.service.model.TelcelBean;

public interface TelcelRepository extends CrudRepository<TelcelBean, Integer> {
	
	@Query("SELECT u FROM TelcelBean u WHERE u.fireTime = (SELECT MAX(fireTime) FROM TelcelBean)")
    TelcelBean findLastUpdate();
}
