package com.github.szabogabriel.minidashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.szabogabriel.minidashboard.data.entites.ConfigurationEntity;
import com.github.szabogabriel.minidashboard.data.entites.DataEntryEntity;
import com.github.szabogabriel.minidashboard.data.entites.DomainEntity;
import com.github.szabogabriel.minidashboard.data.entites.FileEntity;
import com.github.szabogabriel.minidashboard.data.enums.ConfigurationEnum;
import com.github.szabogabriel.minidashboard.data.gui.ConfigurationGui;
import com.github.szabogabriel.minidashboard.data.gui.DataCategoryGui;
import com.github.szabogabriel.minidashboard.data.gui.DataEntryGui;
import com.github.szabogabriel.minidashboard.data.gui.DataRowGui;
import com.github.szabogabriel.minidashboard.data.gui.FileGui;
import com.github.szabogabriel.minidashboard.data.gui.IndexDomainEntry;
import com.github.szabogabriel.minidashboard.data.gui.renderable.RenderableObject;
import com.github.szabogabriel.minidashboard.util.DateUtils;
import com.github.szabogabriel.minidashboard.util.JsonToObjecRendererUtil;

@Service
public class GuiService {

	@Autowired
	private ConfigService configService;

	@Autowired
	private DataEntryService dataEntryService;

	@Autowired
	private DomainService domainService;
	
	@Autowired
	private FileService fileService;

	public List<IndexDomainEntry> getIndexDomainEntries(String selectedDomain) {
		return domainService.getDomainNames().stream().map(e -> map(e, selectedDomain)).collect(Collectors.toList());
	}

	public List<DataCategoryGui> getCurrentDomainData(String domain) {
		List<DataCategoryGui> ret = new ArrayList<>();

		Optional<DomainEntity> dom = domainService.getDomain(domain);

		if (dom.isPresent()) {
			ret = map(dataEntryService.getCurrentEntries(dom.get()).stream().sorted(this::sortByEntry)
					.collect(Collectors.toList()));
		}

		return ret.stream().sorted(this::sortByCategory).collect(Collectors.toList());
	}

	public List<DataCategoryGui> getHistoricData(String domain, String category, String entry) {
		List<DataCategoryGui> ret = new ArrayList<>();

		Optional<DomainEntity> dom = domainService.getDomain(domain);

		if (dom.isPresent()) {
			ret = map(dataEntryService.getEntries(dom.get(), category, entry).stream().sorted(this::sortByLastModified)
					.collect(Collectors.toList()));
		}

		return ret;
	}
	
	public List<FileGui> getFiles() {
		return fileService.getAllEntries().stream().map(this::map).collect(Collectors.toList());
	}

	public List<ConfigurationGui> getConfigs() {
		return configService.getAllEntries().stream().map(this::map).collect(Collectors.toList());
	}

	public void updateConfigs(String key, String value) {
		if (key != null && value != null) {
			configService.setValue(key, value);
		}
	}

	public String getPageTitle() {
		return configService.getValue(ConfigurationEnum.APPLICATION_NAME);
	}

	public String getBanner() {
		return configService.getValue(ConfigurationEnum.BANNER);
	}

	public String getMenuFiles() {
		return configService.getValue(ConfigurationEnum.MENU_FILES);
	}

	public String getButtonAdd() {
		return configService.getValue(ConfigurationEnum.BUTTON_ADD);
	}

	public String getButtonSubmit() {
		return configService.getValue(ConfigurationEnum.BUTTON_SUBMIT);
	}

	public String getViewDataHistory() {
		return configService.getValue(ConfigurationEnum.VIEW_DATA_HISTORY);
	}

	public String getViewFilesTitle() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_TITLE);
	}

	public String getViewFilesFileName() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_FILE_NAME);
	}

	public String getViewFilesMimeType() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_MIME_TYPE);
	}

	public String getViewFilesCreatedAt() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_CREATED_AT);
	}

	public String getViewFilesView() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_VIEW);
	}

	public String getViewFilesDownload() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_DOWNLOAD);
	}

	public String getViewFilesDelete() {
		return configService.getValue(ConfigurationEnum.VIEW_FILES_DELETE);
	}
	
	public void deleteFile(Long fileId) {
		fileService.removeFile(fileId);
	}

	public RenderableObject getRenderableObject(Long fileId) {
		RenderableObject ret = new RenderableObject();
		Optional<FileEntity> file = fileService.findById(fileId);

		if (file.isPresent()) {
			FileEntity f = file.get();

			String fileContent = fileService.getFileContentString(f);
			if ("application/json".equalsIgnoreCase(f.getMimeType())) {
				ret = JsonToObjecRendererUtil.toRenderableObject(fileContent);
			} else {
				ret.setContent(fileContent.replaceAll("\n", "<br/>"));
			}
			ret.setObjectTitle("<h1>" + f.getFileName() + " (" + DateUtils.fromMillies(f.getCreateTimestamp(), configService.getValue(ConfigurationEnum.FORMAT_DATE_GUI)) + ")</h1>");
		}
		
		return ret;
	}

	private int sortByCategory(DataCategoryGui g1, DataCategoryGui g2) {
		return g1.getCategory().compareTo(g2.getCategory());
	}

	private int sortByLastModified(DataEntryEntity e1, DataEntryEntity e2) {
		return (int) (e1.getCreateTimestamp() - e2.getCreateTimestamp());
	}

	private int sortByEntry(DataEntryEntity e1, DataEntryEntity e2) {
		return e1.getEntry().compareTo(e2.getEntry());
	}

	private IndexDomainEntry map(String name, String selected) {
		IndexDomainEntry ret = new IndexDomainEntry();

		ret.setName(name);
		ret.setSelected(name.equals(selected));

		return ret;
	}

	private List<DataCategoryGui> map(List<DataEntryEntity> entities) {
		Map<String, DataCategoryGui> tmp = new HashMap<>();

		int maxLevel = 0;
		for (DataEntryEntity it : entities) {
			DataCategoryGui dcg;
			String category = it.getCategory();
			
			if (!tmp.containsKey(it.getCategory())) {
				String domain = it.getDomain().getName();

				dcg = new DataCategoryGui();
				dcg.setCategory(category);

				dcg.setEntryDescription(configService.getEntryDescription(domain, category));
				String[] tableHeaders = configService.getTableHeaderValue(domain, category);
				
				dcg.setTableHeader0(tableHeaders[0]);
				dcg.setTableHeader1(tableHeaders[1]);
				dcg.setTableHeader2(tableHeaders[2]);
				dcg.setTableHeader3(tableHeaders[3]);
				dcg.setTableHeader4(tableHeaders[4]);
				dcg.setTableHeader5(tableHeaders[5]);
				dcg.setTableHeader6(tableHeaders[6]);
				dcg.setTableHeader7(tableHeaders[7]);	
			} else {
				dcg = tmp.get(category);
			}

			DataEntryGui deg = mapToEntryGui(it);
			if (maxLevel < deg.getMaxIsLevel()) {
				maxLevel = deg.getMaxIsLevel();
				dcg.setMaxIsLevel(maxLevel);
			} else {
				deg.revalidateDataToMaxLevel(maxLevel);
			}
			dcg.addDataEntry(deg);

			tmp.put(it.getCategory(), dcg);
		}

		List<DataCategoryGui> ret = tmp.values().stream().toList();

		return ret;
	}
	
	private DataEntryGui mapToEntryGui(DataEntryEntity dataEntryEntity) {
		DataEntryGui ret = new DataEntryGui();

		ret.setEntry(dataEntryEntity.getEntry());
		ret.setCreated(DateUtils.fromMillies(dataEntryEntity.getCreateTimestamp(), configService.getValue(ConfigurationEnum.FORMAT_DATE_GUI)));
		ret.setLastChanged(DateUtils.fromMillies(dataEntryEntity.getLastModified(), configService.getValue(ConfigurationEnum.FORMAT_DATE_GUI)));
		if (dataEntryEntity.getValidUntil() != null) {
			ret.setValidTo(DateUtils.fromMillies(dataEntryEntity.getValidUntil(), configService.getValue(ConfigurationEnum.FORMAT_DATE_GUI)));
		} else {
			ret.setValidTo("");
		}
		ret.setDomain(dataEntryEntity.getDomain().getName());

		DataRowGui drg = mapToRowGui(dataEntryEntity);
		ret.addDataRow(drg);

		return ret;
	}
	
	private FileGui map(FileEntity file) {
		FileGui ret = new FileGui();
		
		ret.setCreateTime(DateUtils.fromMillies(file.getCreateTimestamp(), configService.getValue(ConfigurationEnum.FORMAT_DATE_GUI)));
		ret.setFileName(file.getFileName());
		ret.setId(file.getFile_id());
		ret.setMimeType(file.getMimeType());
		
		return ret;
	}

	private ConfigurationGui map(ConfigurationEntity entity) {
		ConfigurationGui ret = new ConfigurationGui();

		ret.setKey(entity.getConfKey());
		ret.setValue(entity.getConfValue());
		
		return ret;
	}

	private DataRowGui mapToRowGui(DataEntryEntity entity) {
		DataRowGui ret = new DataRowGui();

		ret.setLevel0(entity.getLevel0());
		ret.setLevel1(entity.getLevel1());
		ret.setLevel2(entity.getLevel2());
		ret.setLevel3(entity.getLevel3());
		ret.setLevel4(entity.getLevel4());
		ret.setLevel5(entity.getLevel5());
		ret.setLevel6(entity.getLevel6());
		ret.setLevel7(entity.getLevel7());

		return ret;
	}

}
