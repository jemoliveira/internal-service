package com.samsung.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsung.service.model.ContactBean;
import com.samsung.service.repository.ContactRepository;
import com.samsung.service.vo.ContactListVO;

@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public ContactListVO findAll(int page, int maxResults) {
        Page<ContactBean> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }

        return buildResult(result);
    }

    public void save(ContactBean contact) {
        contactRepository.save(contact);
    }

    @Secured("ROLE_ADMIN")
    public void delete(int contactId) {
        contactRepository.delete(contactId);
    }

    @Transactional(readOnly = true)
    public ContactListVO findByNameLike(int page, int maxResults, String name) {
        Page<ContactBean> result = executeQueryFindByName(page, maxResults, name);

        if(shouldExecuteSameQueryInLastPage(page, result)) {
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByName(lastPage, maxResults, name);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<ContactBean> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<ContactBean> executeQueryFindAll(int page, int maxResults) {
    	
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

        return contactRepository.findAll(pageRequest);
    }

    private Sort sortByNameASC() {
        return new Sort(Sort.Direction.ASC, "name");
    }
   
    private ContactListVO buildResult(Page<ContactBean> result) {
    	
    	ContactListVO vo = new ContactListVO();
    	vo.setPagesCount(result.getTotalPages());    	
    	vo.setContacts(result.getContent());
        vo.setTotalContacts(result.getTotalElements());
   
        return vo;
    }

    private Page<ContactBean> executeQueryFindByName(int page, int maxResults, String name) {
    	
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

        return contactRepository.findByNameLike(pageRequest, "%" + name + "%");
    }

    private boolean isUserAfterOrOnLastPage(int page, Page<ContactBean> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<ContactBean> result) {
        return result.getTotalElements() > 0;
    }
}