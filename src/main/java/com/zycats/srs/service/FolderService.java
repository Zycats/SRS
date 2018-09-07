package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Folder;
import com.zycats.srs.repository.FolderRepository;

@Service
public class FolderService implements IFolderService {

	@Autowired
	private FolderRepository folderRepository;

	@Override
	public Folder add(Folder folder) {
		return folderRepository.save(folder);
	}

	@Override
	public Iterable<Folder> getAll() {
		return folderRepository.findAll();
	}

	@Override
	public Folder getById(int id) {
		return folderRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		try {
			folderRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
