package com.zycats.srs.service;

import com.zycats.srs.entity.Folder;

public interface IFolderService {

	Folder add(Folder folder);

	Iterable<Folder> getAll();

	Folder getById(int id);

	boolean delete(int id);

}