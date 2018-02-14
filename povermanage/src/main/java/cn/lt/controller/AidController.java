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
import cn.lt.po.Aid;
import cn.lt.po.AidDepartment;
import cn.lt.po.AidStudent;
import cn.lt.po.UserInfo;
import cn.lt.service.AidService;
import cn.lt.service.StudentService;
import cn.lt.vo.IndexVo;
import cn.lt.vo.AidVo;

@Controller
@RequestMapping("/aid")
public class AidController {
	@Autowired
	private AidService aidService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * menu2 路由
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/aidPage")
	public ModelAndView aidPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		String urlStr = "";
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1){
				urlStr = "aidPage";
			}else{
				urlStr = "aidDepartmentPage";
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
				//超管显示p_aid
				List<Aid> list = aidService.findAid(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}else{
				//院系管理员显示p_aid_department
				if(user.getdId()!=null && user.getdId()!=0){
					vo.setdId(user.getdId());
				}
				List<AidDepartment> list = aidService.findAidDepartment(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}
		}
        return map;
    }
	
	/**
	 * 删除助学贷款条目  id （aid的id�?
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delAid")
	@ResponseBody
    public Map<String,Object> delAid(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = aidService.delAid(vo);
        return map;
    }
	
	/**
	 * 添加助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addAid")
	@ResponseBody
    public Map<String,Object> addAid(HttpServletRequest request,AidVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = aidService.editAid(vo);
		}else{
			map = aidService.addAid(vo);
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
	    return new ModelAndView("addAid", model);
	}
	
	@RequestMapping("/addADPage")
	public ModelAndView addADPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		List<Department> dList = studentService.findDepartment(vo);
		model.addAttribute("dList", dList);
		model.addAttribute("aidId", vo.getAidId());
	    return new ModelAndView("addAD", model);
	}
	
	@RequestMapping("/delAD")
	@ResponseBody
    public Map<String,Object> delAD(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = aidService.delAD(vo);
        return map;
    }
	
	@RequestMapping("/addAD")
	@ResponseBody
    public Map<String,Object> addAD(HttpServletRequest request,AidVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = aidService.editAD(vo);
		}else{
			map = aidService.addAD(vo);
		}
        return map;
    }
    
	@RequestMapping("/addAS")
	@ResponseBody
    public Map<String,Object> addAS(HttpServletRequest request,AidVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			//判断是否是本院系学生
			vo.setdId(user.getdId());
			
			vo.setAdminId(user.getId());
			map = aidService.addAS(vo);
		}
        return map;
    }
	
	@RequestMapping("/aidStudent")
	public ModelAndView aidStudent(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		model.addAttribute("aidId", vo.getAidId());
		model.addAttribute("dId", vo.getdId());
	    return new ModelAndView("aidStudent", model);
	}
	
	@RequestMapping("/allAS")
	@ResponseBody
    public Map<String,Object> allAS(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getdId()!=0){
				vo.setdId(user.getdId());
			}
			List<AidStudent> list = aidService.allAS(vo);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", list.size());
			map.put("data", list);			
		}
        return map;
    }
	
	@RequestMapping("/delAS")
	@ResponseBody
    public Integer delAS(HttpServletRequest request,IndexVo vo){
		Integer count = aidService.delAS(vo);
        return count;
    }
}
