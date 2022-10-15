package com.shf.dcs.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

/**
 * Control for module admin and its functions.
 * 
 * @author cuongnguyen
 *
 */
@Controller
public class AdminController {

	private static Logger logger = Logger.getLogger(AdminController.class);
	
	// -----------------------------------------------------------------------
	@Autowired
	public IUserService userService;
	
	// -----------------------------------------------------------------------
	
	@RequestMapping(value = "/")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		return model;
	}
	
	/*@RequestMapping(value = "/home")
	public ModelAndView blank() {
		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("subpage", "blank");
		return model;
	}*/

	@RequestMapping(value = "/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		try {
			List<String> listRoleChildId = (List<String>) request.getSession().getAttribute("listMenuLeftChild");
			request.getSession().removeAttribute(Constants.PRIVILEGE_ADHOC_ALL);
			for (String privilegeId : listRoleChildId) {
				if(isKeyword(Constants.PRIVILEGE_ID_CALLER,privilegeId)) {
					request.getSession().setAttribute(Constants.PRIVILEGE_ADHOC_ALL , Constants.PRIVILEGE_ADHOC_ALL);
					return new ModelAndView("redirect:/dashboard/callerSoftCall/load");
				} else if(isKeyword(Constants.PRIVILEGE_ID_HARD,privilegeId)) {
					request.getSession().setAttribute(Constants.PRIVILEGE_ADHOC_ALL , Constants.PRIVILEGE_ADHOC_ALL);
					return new ModelAndView("redirect:/dashboard/callerSoftCall/load");
				} else if (Constants.PRIVILEGE_ID_CALL_TEAMLEADER.equals(privilegeId)) {
					return new ModelAndView("redirect:/dashboard/leaderSoftCall/load");
				} else if (Constants.PRIVILEGE_ID_HARD_TEAMLEADER.equals(privilegeId)) {
					return new ModelAndView("redirect:/dashboard/leaderHardField/load");
				} else if (Constants.PRIVILEGE_ID_ADHOC.equals(privilegeId)) {
					return new ModelAndView("redirect:/dashboard/adhocSearch/load");
				} 
			}
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new ModelAndView("/invalidSession");
		}
		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("subpage", "blank");
		return model;
		// return "/invalidSession";
	}
	
	public static boolean isKeyword(String[] keywords,String keyword) {
        return (Arrays.binarySearch(keywords, keyword) >= 0);
    }
}
