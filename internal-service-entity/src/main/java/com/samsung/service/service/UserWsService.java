package com.samsung.service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsung.service.jdbcTemplate.UserWsTemplate;
import com.samsung.service.vo.UserWsListVO;
import com.samsung.service.vo.UserWsVO;

@Service
@Transactional
public class UserWsService {
	
	private static UserWsListVO buildResult(Page<UserWsVO> result) {

		UserWsListVO vo = new UserWsListVO();
		vo.setPagesCount(result.getTotalPages());    	
		vo.setList(result.getContent());
		vo.setTotalList(result.getTotalElements());

		return vo;
	}
	
	private static Sort sortByNameASC() {
        return new Sort(Sort.Direction.ASC, "name");
    }
	
	private static Page<UserWsVO> executeQueryFindAll(int page, int maxResults) {
		
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());
        return UserWsTemplate.findAll(pageRequest);
        
    }
	
	@Transactional(readOnly = true)
    public static UserWsListVO findAll(int page, int maxResults) {
        Page<UserWsVO> result = executeQueryFindAll(page, maxResults);

        return buildResult(result);
    }
	
	private Page<UserWsVO> executeQueryFindByName(int page, int maxResults, String name) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

        return UserWsTemplate.findByNameLike(pageRequest, "%" + name + "%");
    }
	
	@Transactional(readOnly = true)
    public UserWsListVO findByNameLike(int page, int maxResults, String name) {
        Page<UserWsVO> result = executeQueryFindByName(page, maxResults, name);

        if(shouldExecuteSameQueryInLastPage(page, result)) {
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByName(lastPage, maxResults, name);
        }

        return buildResult(result);
    }
	
	private boolean shouldExecuteSameQueryInLastPage(int page, Page<UserWsVO> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }
	
	private boolean isUserAfterOrOnLastPage(int page, Page<UserWsVO> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<UserWsVO> result) {
        return result.getTotalElements() > 0;
    }


}