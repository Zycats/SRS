package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.exception.InsufficientPriviledgesException;
import com.zycats.srs.service.ICommentService;

@RestController
@RequestMapping("/rest/comment/*")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Comment addComment(@RequestBody Comment comment, Authentication auth)
			throws InsufficientPriviledgesException {
		return commentService.add(comment, auth);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean deleteComment(@PathVariable int id) {
		return commentService.delete(id);
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public Comment getCommentById(@PathVariable int id) {
		return commentService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Comment> getAllComment() {
		return commentService.getAll();
	}

	@RequestMapping(value = "get/ticket/{id}", method = RequestMethod.GET)
	public Iterable<Comment> getByTicketId(@PathVariable int id) {
		return commentService.getByTicketId(id);
	}

}
