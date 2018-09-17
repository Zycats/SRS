package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.exception.InsufficientPriviledgesException;

public interface ICommentService {

	Iterable<Comment> getAll();

	Comment getById(int id);

	boolean delete(int id);

	Comment add(Comment comment, Authentication auth) throws InsufficientPriviledgesException;

	Iterable<Comment> getByTicketId(int ticketId);

}