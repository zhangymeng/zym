package cn.lt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lt.po.Department;
import cn.lt.po.Social;
import cn.lt.po.SocialDepartment;
import cn.lt.po.SocialStudent;
import cn.lt.po.UserInfo;
import cn.lt.service.SocialService;
import cn.lt.service.StudentService;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

@Controller
@RequestMapping("/social")
public class SocialController {
	@Autowired
	private SocialService socialService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * menu2 路由
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/socialPage")
	public ModelAndView SocialPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		String urlStr = "";
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1){
				urlStr = "socialPage";
			}else{
				urlStr = "socialDepartmentPage";
			}
		}
	    return new ModelAndView(urlStr, model);
	}
	
	/**
	 * 助学贷款列表   院系助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1 && vo.getType()==null){
				//超管显示p_Social
				List<Social> list = socialService.findSocial(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}else{
				//院系管理员显示p_Social_department
				if(user.getdId()!=null && user.getdId()!=0){
					vo.setdId(user.getdId());
				}
				List<SocialDepartment> list = socialService.findSocialDepartment(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}
		}
        return map;
    }
	
	/**
	 * 删除助学贷款条目  id （Social的id�?
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delSocial")
	@ResponseBody
    public Map<String,Object> delSocial(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = socialService.delSocial(vo);
        return map;
    }
	
	/**
	 * 添加助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addSocial")
	@ResponseBody
    public Map<String,Object> addSocial(HttpServletRequest request,SocialVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = socialService.editSocial(vo);
		}else{
			map = socialService.addSocial(vo);
		}
		
        return map;
    }
	
	/**
	 * 添加院系助学贷款写页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public ModelAndView addPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
	    return new ModelAndView("addSocial", model);
	}
	
	@RequestMapping("/addSDPage")
	public ModelAndView addSDPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		List<Department> dList = studentService.findDepartment(vo);
		model.addAttribute("dList", dList);
		model.addAttribute("sId", vo.getsId());
	    return new ModelAndView("addSD", model);
	}
	
	@RequestMapping("/delSD")
	@ResponseBody
    public Map<String,Object> delSD(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = socialService.delSD(vo);
        return map;
    }
	
	@RequestMapping("/addSD")
	@ResponseBody
    public Map<String,Object> addSD(HttpServletRequest request,SocialVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = socialService.editSD(vo);
		}else{
			map = socialService.addSD(vo);
		}
        return map;
    }
    
	@RequestMapping("/addSS")
	@ResponseBody
    public Map<String,Object> addSS(HttpServletRequest request,SocialVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			//判断是否是本院系学生
			vo.setdId(user.getdId());
			
			vo.setAdminId(user.getId());
			map = socialService.addSS(vo);
		}
        return map;
    }
	
	@RequestMapping("/socialStudent")
	public ModelAndView SocialStudent(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		model.addAttribute("sId", vo.getsId());
		model.addAttribute("dId", vo.getdId());
	    return new ModelAndView("socialStudent", model);
	}
	
	@RequestMapping("/allSS")
	@ResponseBody
    public Map<String,Object> allSS(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getdId()!=0){
				vo.setdId(user.getdId());
			}
			List<SocialStudent> list = socialService.allSS(vo);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", list.size());
			map.put("data", list);			
		}
        return map;
    }
	
	@RequestMapping("/delSS")
	@ResponseBody
    public Integer delSS(HttpServletRequest request,IndexVo vo){
		Integer count = socialService.delSS(vo);
        return count;
    }
}
