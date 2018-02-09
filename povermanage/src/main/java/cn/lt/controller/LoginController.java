package cn.lt.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lt.po.Menu;
import cn.lt.po.Role;
import cn.lt.po.UserInfo;
import cn.lt.service.UserService;
import cn.lt.vo.UserVo;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginPage")
    public ModelAndView loginPage(UserVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("login", model);
    }
	
	/**
	 * 登录 username password
	 * @param vo
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
    public Map<String,Object> login(UserVo vo,HttpServletRequest request){
		 Map<String,Object> map = userService.login(vo);
		 
		 boolean result = (Boolean) map.get("result");
		 String idStr = (String) map.get("reason");
		 
		 if(result){
			 Integer id = Integer.parseInt(idStr);
			 UserInfo user = userService.getUserById(id);
			 request.getSession().setAttribute("user", user);
		 }
		 
		 return map;
    }
	
	/**
	 * 登录后主页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
		String urlStr = "login";
		ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			urlStr = "index";
			model.addAttribute("userInfo", user);
			//菜单
			List<Menu> menuList = userService.getMenu(user.getRoleId());
			model.addAttribute("menuList", menuList);
		}
        
        return new ModelAndView(urlStr, model);
    }
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
		ModelMap model = new ModelMap();
		Enumeration<?> em = request.getSession().getAttributeNames();
		  while(em.hasMoreElements()){
		   request.getSession().removeAttribute(em.nextElement().toString());
		}
        return new ModelAndView("login", model);
    }
	
	
	@RequestMapping("/basic")
    public ModelAndView basic(HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("basic", model);
    }
}
