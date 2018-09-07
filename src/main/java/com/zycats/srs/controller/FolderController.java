package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Folder;
import com.zycats.srs.service.IFolderService;

@RestController
@RequestMapping("rest/folder/*")
public class FolderController {

	private IFolderService folderService;

	@RequestMapping(name = "add")
	public Folder addFolder(@RequestBody Folder folder) {
		return folderService.add(folder);
	}

	@RequestMapping(name = "delete/{id}")
	public boolean addFolder(@PathVariable int id) {
		return folderService.delete(id);
	}

	@RequestMapping(name = "get/{id}")
	public Folder getFolderById(@PathVariable int id) {
		return folderService.getById(id);
	}

	@RequestMapping(name = "get/all")
	public Iterable<Folder> getAllFolder() {
		return folderService.getAll();
	}

}
