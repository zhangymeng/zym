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
import cn.lt.po.Scholarship;
import cn.lt.po.ScholarshipDepartment;
import cn.lt.po.ScholarshipStudent;
import cn.lt.po.UserInfo;
import cn.lt.service.ScholarshipService;
import cn.lt.service.StudentService;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipController {
	@Autowired
	private ScholarshipService scholarshipService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * menu2 路由
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/scPage")
	public ModelAndView ScholarshipPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		String urlStr = "";
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1){
				urlStr = "scPage";
			}else{
				urlStr = "scDepartmentPage";
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
				//超管显示p_Scholarship
				List<Scholarship> list = scholarshipService.findScholarship(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}else{
				//院系管理员显示p_Scholarship_department
				if(user.getdId()!=null && user.getdId()!=0){
					vo.setdId(user.getdId());
				}
				List<ScholarshipDepartment> list = scholarshipService.findScholarshipDepartment(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}
		}
        return map;
    }
	
	/**
	 * 删除助学贷款条目  id （Scholarship的id�?
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delScholarship")
	@ResponseBody
    public Map<String,Object> delScholarship(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = scholarshipService.delScholarship(vo);
        return map;
    }
	
	/**
	 * 添加助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addScholarship")
	@ResponseBody
    public Map<String,Object> addScholarship(HttpServletRequest request,SocialVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = scholarshipService.editScholarship(vo);
		}else{
			map = scholarshipService.addScholarship(vo);
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
	    return new ModelAndView("addScholarship", model);
	}
	
	@RequestMapping("/addSDPage")
	public ModelAndView addSDPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		List<Department> dList = studentService.findDepartment(vo);
		model.addAttribute("dList", dList);
		model.addAttribute("sId", vo.getsId());
	    return new ModelAndView("addScD", model);
	}
	
	@RequestMapping("/delSD")
	@ResponseBody
    public Map<String,Object> delSD(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = scholarshipService.delSD(vo);
        return map;
    }
	
	@RequestMapping("/addSD")
	@ResponseBody
    public Map<String,Object> addSD(HttpServletRequest request,SocialVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = scholarshipService.editSD(vo);
		}else{
			map = scholarshipService.addSD(vo);
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
			map = scholarshipService.addSS(vo);
		}
        return map;
    }
	
	@RequestMapping("/scStudent")
	public ModelAndView ScholarshipStudent(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		model.addAttribute("sId", vo.getsId());
		model.addAttribute("dId", vo.getdId());
	    return new ModelAndView("scStudent", model);
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
			List<ScholarshipStudent> list = scholarshipService.allSS(vo);
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
		Integer count = scholarshipService.delSS(vo);
        return count;
    }
}
