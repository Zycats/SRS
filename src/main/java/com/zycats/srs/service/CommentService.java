package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.repository.CommentRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment add(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Iterable<Comment> getAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment getById(int id) {
		return commentRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		try {
			commentRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
