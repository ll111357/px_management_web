package com.px.web.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.px.dto.ResourceDTO;
import com.px.entity.Resource;
import com.px.service.ResourceService;
import com.px.service.RoleResourceRelService;
/**
 * 资源管理Controller
 * @author Jianfang Xu
 *
 */
@Controller
@RequestMapping("/boss/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleResourceRelService roleResourceRelService;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		return "system/resource/list";
	}
	
	@RequestMapping("/demo")
	public String demo(){
		
		return "system/resource/demo";
	}
	
	@RequestMapping("/add")
	public String toAdd(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		Resource rootResource = null;
		Resource resource = null;
		List<Resource> resourceList = resourceService.getRootResourceList();
		if(StringUtils.isNotBlank(id)){
			resource = resourceService.find(id);
		}
		if(resource != null){
			if(resource.getParent() != null){
				rootResource = resource.getParent();
			}else{
				rootResource = resource;
			}
		}
		model.addAttribute("id", id);
		model.addAttribute("resource", resource);
		model.addAttribute("rootResource", rootResource);
		model.addAttribute("resourceList", resourceList);
		
		return "system/resource/add";
	}
	
	@RequestMapping("/saveorupdate")
	public String doSaveOrUpdate(HttpServletRequest request){
		String id = request.getParameter("id"); //记录ID
		String pid = request.getParameter("pid");//父ID
		String name = request.getParameter("name");
		
		if(StringUtils.isNotBlank(id)){
			Resource resource = resourceService.find(id);
			if(resource != null){
				resource.setName(name);
				resource.setCreateDate(new Date());
				if(StringUtils.isNotBlank(pid)){
					Resource parentResource = resourceService.find(pid);
					resource.setParent(parentResource);
				}else{
					resource.setParent(null);
				}
				resourceService.update(resource);
			}
		}else{
			Resource resource = new Resource();
			resource.setName(name);
			resource.setCreateDate(new Date());
			if(StringUtils.isNotBlank(pid)){
				Resource parentResource = resourceService.find(pid);
				resource.setParent(parentResource);
			}
			resourceService.save(resource);
		}
		
		return "redirect:/boss/resource/list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("sucess", false);
		String msg = "";
		try{
			String id = request.getParameter("id");
			if(StringUtils.isNotBlank(id)){
				Resource resource = resourceService.find(id);
				resourceService.delete(resource);
				resultMap.put("sucess", true);
				msg = "操作成功!";
			}else{
				msg = "请选择记录！";
			}
		}catch(Exception e){
			msg = "操作失败!";
			e.printStackTrace();
		}
		resultMap.put("msg", msg);
		return resultMap;
	}
	
	@RequestMapping("/ajax_list")
	@ResponseBody
	public List<ResourceDTO> ajaxList(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<Resource> resourceList = null;
		if(StringUtils.isNotBlank(roleId)){
			resourceList = roleResourceRelService.findResourceListByRole(roleId);
		}else{
			resourceList = resourceService.getAll();
		}
		List<ResourceDTO> resourceDTOList = new ArrayList<ResourceDTO>();
		if(resourceList != null && resourceList.size() > 0){
			for(Resource resource : resourceList){
				if(resource.getParent() == null){
					ResourceDTO resourceDTO = new ResourceDTO();
					resourceDTO.setId(resource.getId());
					resourceDTO.setName(resource.getName());
					resourceDTO.setType(resource.getType());
					resourceDTO.setUrl(resource.getUrl());
					resourceDTOList.add(resourceDTO);
				}
			}
			
			for(ResourceDTO rDTO : resourceDTOList){
				for(Iterator<Resource> rt = resourceList.iterator();rt.hasNext();){
					Resource r = rt.next();
					if(r.getParent() != null){
						if(rDTO.getId().equals(r.getParent().getId())){
							if(rDTO.getChildren() == null){
								rDTO.setChildren(new ArrayList<ResourceDTO>());
							}
							ResourceDTO childResourceDTO = new ResourceDTO();
							childResourceDTO.setId(r.getId());
							childResourceDTO.setName(r.getName());
							childResourceDTO.setType(r.getType());
							childResourceDTO.setUrl(r.getUrl());
							rDTO.getChildren().add(childResourceDTO);
						}
					}
				}
			}
		}
		return resourceDTOList;
	}
}
