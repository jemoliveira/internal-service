package com.samsung.service.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samsung.service.jdbcTemplate.UserWsTemplate;
import com.samsung.service.service.UserWsService;
import com.samsung.service.vo.UserWsListVO;
import com.samsung.service.vo.UserWsVO;

@Controller
@RequestMapping(value = "/protected/userWs")
public class UserWsController {
	
	private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
	
	@Autowired
    private UserWsService userWsService;

	@Autowired
	private MessageSource messageSource;
	
	private String result = null;

	@Value("1000")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("userList");
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("bean") UserWsVO bean,                                    
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
		
		result = UserWsTemplate.insert(bean);
		
		if (result.equals("0")) {
			search(bean.getWsUser(), page, locale, "message.create.success");
		}
		
        return createListAllResponse(page, locale, null);
    }
	
	@RequestMapping(value = "/{wsUser}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<?> update(@PathVariable("wsUser") final String wsUser,
									@RequestBody UserWsVO userWsVO,
									@RequestParam(required=false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
									Locale locale) {
		
		if (isUserWsNotEqual(wsUser, userWsVO)) {
			return returnBadRequestResponse();
		}
		
		result = UserWsTemplate.update(userWsVO);
		
		if (result.equals("0")) {
			search(wsUser, page, locale, "message.update.success");
		}
		
		return createListAllResponse(page, locale, "message.update.success");
	}
	
	@RequestMapping(value = "/{wsUser}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable("wsUser") final String wsUser,
									@RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
									@RequestParam(required = false, defaultValue = "") String searchFor,
									Locale locale) {
		
		result = UserWsTemplate.delete(wsUser);
		
		if (result.equals("0")) {
			search(wsUser, page, locale, "message.delete.success");
		}
		
		return createListAllResponse(page, locale, "message.delete.success");
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
		return createListAllResponse(page, locale);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
		return createListAllResponse(page, locale, null);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {

		UserWsListVO listVO = listAll(page);

		addActionMessageToVO(listVO, locale, messageKey, null);

		return returnListToUser(listVO);
	}

	private UserWsListVO listAll(int page) {
		return UserWsService.findAll(page, maxResults);
	}

	private UserWsListVO addActionMessageToVO(UserWsListVO listVO, Locale locale, String actionMessageKey, 
			Object[] args) {

		if (StringUtils.isEmpty(actionMessageKey)) {
			listVO.setSearchMessage(result);
			return listVO;
		}

		listVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

		return listVO;
	}
	
	private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
		
		UserWsListVO listVO = userWsService.findByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(listVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(listVO, locale, "message.search.for.active", args);

        return new ResponseEntity<UserWsListVO>(listVO, HttpStatus.OK);
    }
	
	private UserWsListVO addSearchMessageToVO(UserWsListVO listVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return listVO;
        }

        listVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return listVO;
    }
	
	private ResponseEntity<UserWsListVO> returnListToUser(UserWsListVO list) {
		return new ResponseEntity<UserWsListVO>(list, HttpStatus.OK);
	}
	
	private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
	
	private boolean isUserWsNotEqual(String wsUser, UserWsVO userWsVO) {
		return (wsUser != null && userWsVO != null) && !wsUser.equals(userWsVO.getWsUser());
	}
	
	private ResponseEntity<String> returnBadRequestResponse() {
		return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
	}
}