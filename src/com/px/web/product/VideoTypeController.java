package com.px.web.product;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.px.common.entity.PageModel;
import com.px.entity.VideoType;
import com.px.service.VideoTypeService;

/**
 * 视频类型管理类
 * @author pingan
 *
 */

@Controller
@RequestMapping("/boss/product/videoType")
public class VideoTypeController {

	@Autowired
	private VideoTypeService videoTypeService;
	
	@RequestMapping("/list")
	public String videoTypePageList(HttpServletRequest request,Model model){
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageModel<VideoType> page = videoTypeService.queryForPage(id, name, currentPage, pageSize);
		model.addAttribute("page", page);
		
		return "boss/product/videoTypeList";
	}
	
	@RequestMapping("/save")
	public String saveVideoType(VideoType videoType){
		if(StringUtils.isNotBlank(videoType.getId())){
			videoTypeService.update(videoType);
		}else{
			videoTypeService.save(videoType);
		}
		return "redirect:/boss/product/videoType/list";
	}
	
	@RequestMapping("/edit")
	public String editVideoType(Model model){

		return "boss/product/videoTypeEdit";
	}
	
	
}
