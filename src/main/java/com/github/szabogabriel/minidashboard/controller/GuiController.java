package com.github.szabogabriel.minidashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.szabogabriel.minidashboard.data.gui.renderable.RenderableObject;
import com.github.szabogabriel.minidashboard.service.GuiService;

@Controller
public class GuiController {

	@Autowired
	private GuiService guiService;
	
	@GetMapping(value="/", produces="text/html")
	public ModelAndView indexEmpty() {
		return index("");
	}

	@GetMapping(value="/index", produces="text/html")
	public ModelAndView index(@RequestParam(defaultValue = "") String domain) {
		ModelAndView ret = createModelAndView();

		ret.addObject("data.entries", guiService.getCurrentDomainData(domain));

		if (domain != null && domain.trim().length() > 0) {
			ret.addObject("show.data", Boolean.TRUE);
		} else {
			ret.addObject("show.banner", Boolean.TRUE);
			ret.addObject("banner", "banner.png");
		}

		return ret;
	}

	@GetMapping(value="/history/{domain}/{category}/{entry}", produces="text/html")
	public ModelAndView history(@PathVariable("domain") String domain, 
		@PathVariable("category") String category,
		@PathVariable("entry") String entry,
		@RequestParam(defaultValue = "0") int page) {

		ModelAndView ret = createModelAndView();

		String numItemsEnvVar = guiService.getNumberOfHistoryItems();
		numItemsEnvVar = (numItemsEnvVar != null) ? numItemsEnvVar : "50";
		int numberOfHistoryItems = Integer.parseInt(numItemsEnvVar);

		page = page < 0 ? 0 : page;
		var totalPages = guiService.getTotalPages(domain, category, entry, numberOfHistoryItems);

		ret.addObject("data.entries", guiService.getHistoricData(domain, category, entry, page, numberOfHistoryItems));
		ret.addObject("size", numberOfHistoryItems);
		ret.addObject("previous", page - 1);
		ret.addObject("next", page + 1);
		ret.addObject("lastPage", totalPages - 1);
		ret.addObject("notFirstPage", page == 0 ? false : true);
		ret.addObject("notLastPage", page == (totalPages - 1) ? false : true);

		
		if (domain != null && domain.trim().length() > 0) {
			ret.addObject("show.history", Boolean.TRUE);
		}
		
		return ret;
	}
	
	@GetMapping(value="/files", produces="text/html")
	public ModelAndView files() {
		ModelAndView ret = createModelAndView();
		
		ret.addObject("show.files", Boolean.TRUE);
		ret.addObject("fileEntries", guiService.getFiles());
		
		return ret;
	}
	
	@GetMapping(value="/file/delete/{fileId}", produces="text/html")
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

	@GetMapping(value="/config", produces="text/html")
	public ModelAndView getConfig() {
		ModelAndView ret = createModelAndView();

		ret.addObject("show.config", Boolean.TRUE);
		ret.addObject("config.values", guiService.getConfigs());

		return ret;
	}

	@GetMapping(value="/guifile/{fileId}", produces="text/html")
	public ModelAndView viewFile(@PathVariable("fileId")String fileId) {
		ModelAndView ret = createModelAndView();

		if (fileId != null) {
			try {
				Long fileIdLong = Long.parseLong(fileId);
				RenderableObject data = guiService.getRenderableObject(fileIdLong);
				if (data != null) {
					ret.addObject("show.file", Boolean.TRUE);
					List<RenderableObject> tmp = new ArrayList<>();
					tmp.add(data);
					ret.addObject("objectList", tmp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ret = indexEmpty();
		}

		return ret;
	}

	@PostMapping(value="/config", produces="text/html")
	public ModelAndView updateConfig(@RequestParam("key") String key, @RequestParam("value") String value) {
		guiService.updateConfigs(key, value);

		return getConfig();
	}

	private ModelAndView createModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("page");
		modelAndView.addObject("known.domains", guiService.getIndexDomainEntries(""));
		modelAndView.addObject("page.title", guiService.getPageTitle());
		modelAndView.addObject("menu.files", guiService.getMenuFiles());
		modelAndView.addObject("button.add", guiService.getButtonAdd());
		modelAndView.addObject("button.submit", guiService.getButtonSubmit());
		modelAndView.addObject("string.created", guiService.getStringCreated());
		modelAndView.addObject("string.lastChanged", guiService.getStringLastChanged());
		modelAndView.addObject("string.validUntil", guiService.getStringValidUntil());
		modelAndView.addObject("view.data.history", guiService.getViewDataHistory());
		modelAndView.addObject("view.files.title", guiService.getViewFilesTitle());
		modelAndView.addObject("view.files.file.name", guiService.getViewFilesFileName());
		modelAndView.addObject("view.files.mime.type", guiService.getViewFilesMimeType());
		modelAndView.addObject("view.files.created.at", guiService.getViewFilesCreatedAt());
		modelAndView.addObject("view.files.view", guiService.getViewFilesView());
		modelAndView.addObject("view.files.download", guiService.getViewFilesDownload());
		modelAndView.addObject("view.files.delete", guiService.getViewFilesDelete());
		return modelAndView;
	}

}
