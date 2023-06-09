package com.github.szabogabriel.minidashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.szabogabriel.minidashboard.service.GuiService;

@Controller
public class GuiController {

	@Autowired
	private GuiService guiService;

	@GetMapping("/index")
	public ModelAndView index(@RequestParam(defaultValue = "") String domain) {
		ModelAndView ret = new ModelAndView();

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
		ModelAndView ret = new ModelAndView();
		
		ret.setViewName("page");
		
		ret.addObject("known.domains", guiService.getIndexDomainEntries(domain));
		ret.addObject("data.entries", guiService.getHistoricData(domain, category, entry));
		
		if (domain != null && domain.trim().length() > 0) {
			ret.addObject("show.history", Boolean.TRUE);
		}
		
		return ret;
	}

}
