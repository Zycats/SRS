package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.service.ICommentService;

@RestController
@RequestMapping("rest/comment/*")
public class CommentController {

	private ICommentService commentService;

	@RequestMapping(name = "add")
	public Comment addComment(@RequestBody Comment comment) {
		return commentService.add(comment);
	}

	@RequestMapping(name = "delete/{id}")
	public boolean addComment(@PathVariable int id) {
		return commentService.delete(id);
	}

	@RequestMapping(name = "get/{id}")
	public Comment getCommentById(@PathVariable int id) {
		return commentService.getById(id);
	}

	@RequestMapping(name = "get/all")
	public Iterable<Comment> getAllComment() {
		return commentService.getAll();
	}

}
