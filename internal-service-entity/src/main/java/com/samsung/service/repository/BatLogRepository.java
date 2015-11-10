package com.samsung.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.samsung.service.model.BatLogBean;

public interface BatLogRepository extends PagingAndSortingRepository<BatLogBean, Integer> {
	
	@Query("select u FROM BatLogBean u WHERE u.runDateTime = (SELECT MAX(runDateTime) FROM BatLogBean where batchName = 'ALL_T')")
    BatLogBean allTransactionFind();
	
	@Query("select u FROM BatLogBean u WHERE u.runDateTime = (SELECT MAX(runDateTime) FROM BatLogBean where batchName = 'TMS')")
    BatLogBean tmsFind();
	
	@Query("select u FROM BatLogBean u WHERE u.runDateTime = (SELECT MAX(runDateTime) FROM BatLogBean where batchName = 'SAW_Risk')")
    BatLogBean sawFind();
	
	Page<BatLogBean> findByNameLike(Pageable pageable, String name);
}
