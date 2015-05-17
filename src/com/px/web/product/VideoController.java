package com.px.web.product;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.px.common.entity.PageModel;
import com.px.entity.Video;
import com.px.entity.VideoType;
import com.px.service.VideoService;
import com.px.service.VideoTypeService;

/**
 * 视频controller
 * @author pingan
 *
 */

@Controller
@RequestMapping("/boss/product/video")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoTypeService videoTypeService;
	
	@RequestMapping("/list")
	public String pageList(HttpServletRequest request,Model model,Video queryVideo){
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");
		
		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageModel<Video> page = videoService.queryForPage(queryVideo, currentPage, pageSize);
		model.addAttribute("page", page);
		
		return "boss/product/videoList";
	}
	
	@RequestMapping("/save")
	public String saveVideoType(Video video){
		if(StringUtils.isNotBlank(video.getId())){
			video.setUpdateDate(new Date());
			videoService.update(video);
		}else{
			video.setCreateDate(new Date());
			videoService.save(video);
		}
		return "redirect:/boss/product/video/list";
	}
	
	@RequestMapping("/edit")
	public String editVideoType(Model model){

		 PageModel<VideoType> page = videoTypeService.queryForPage("", "", 1, 20);
		 model.addAttribute("videoTypeList",page.getList());
		
		 return "boss/product/videoEdit";
	}
}
