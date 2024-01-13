package com.github.szabogabriel.minidashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.szabogabriel.minidashboard.service.GuiService;

@Controller
public class GuiController {

	@Autowired
	private GuiService guiService;
	
	@GetMapping("/")
	public ModelAndView indexEmpty() {
		return index("");
	}

	@GetMapping("/index")
	public ModelAndView index(@RequestParam(defaultValue = "") String domain) {
		ModelAndView ret = createModelAndView();

		ret.setViewName("page");

		ret.addObject("known.domains", guiService.getIndexDomainEntries(domain));
		ret.addObject("data.entries", guiService.getCurrentDomainData(domain));

		if (domain != null && domain.trim().length() > 0) {
			ret.addObject("show.data", Boolean.TRUE);
		}

		return ret;
	}

	@GetMapping("/history/{domain}/{category}/{entry}")
	public ModelAndView history(@PathVariable("domain") String domain, @PathVariable("category") String category,
			@PathVariable("entry") String entry) {
		ModelAndView ret = createModelAndView();
		
		ret.setViewName("page");
		
		ret.addObject("known.domains", guiService.getIndexDomainEntries(domain));
		ret.addObject("data.entries", guiService.getHistoricData(domain, category, entry));
		
		if (domain != null && domain.trim().length() > 0) {
			ret.addObject("show.history", Boolean.TRUE);
		}
		
		return ret;
	}
	
	@GetMapping("/files")
	public ModelAndView files() {
		ModelAndView ret = createModelAndView();
		
		ret.setViewName("page");
		ret.addObject("show.files", Boolean.TRUE);
		ret.addObject("known.domains", guiService.getIndexDomainEntries(""));
		ret.addObject("fileEntries", guiService.getFiles());
		
		return ret;
	}
	
	@GetMapping("/file/delete/{fileId}")
	public ModelAndView fileDelete(@PathVariable("fileId")String fileId) {
		if (fileId != null) {
			try {
				Long fileIdLong = Long.parseLong(fileId);
				guiService.deleteFile(fileIdLong);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return files();
	}

	@GetMapping("/config")
	public ModelAndView getConfig() {
		ModelAndView ret = createModelAndView();

		ret.setViewName("page");
		ret.addObject("show.config", Boolean.TRUE);
		ret.addObject("config.values", guiService.getConfigs());

		return ret;
	}

	@PostMapping("/config")
	public ModelAndView updateConfig(@RequestParam("key") String key, @RequestParam("value") String value) {
		guiService.updateConfigs(key, value);

		return getConfig();
	}

	private ModelAndView createModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("page.title", guiService.getPageTitle());
		return modelAndView;
	}

}
