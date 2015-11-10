package com.samsung.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.samsung.service.model.ContactBean;

public interface ContactRepository extends PagingAndSortingRepository<ContactBean, Integer> {
    Page<ContactBean> findByNameLike(Pageable pageable, String name);
}