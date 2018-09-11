package com.zycats.srs.service;

import com.zycats.srs.entity.Comment;

public interface ICommentService {

	Comment add(Comment comment);

	Iterable<Comment> getAll();

	Comment getById(int id);

	boolean delete(int id);

}