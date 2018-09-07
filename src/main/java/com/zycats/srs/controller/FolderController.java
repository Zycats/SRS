package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Folder;
import com.zycats.srs.service.IFolderService;

@RestController
@RequestMapping("/rest/folder/*")
public class FolderController {

	private IFolderService folderService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Folder addFolder(@RequestBody Folder folder) {
		return folderService.add(folder);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean deleteFolder(@PathVariable int id) {
		return folderService.delete(id);
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public Folder getFolderById(@PathVariable int id) {
		return folderService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Folder> getAllFolder() {
		return folderService.getAll();
	}

}
