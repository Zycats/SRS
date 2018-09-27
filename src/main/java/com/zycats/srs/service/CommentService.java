package com.zycats.srs.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zycats.srs.aspects.Demo;
import com.zycats.srs.entity.Comment;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.exception.InsufficientPriviledgesException;
import com.zycats.srs.repository.CommentRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private IEmployeeService employeeService;

	@Demo
	@Override
	public Comment add(Comment comment, Authentication auth) throws InsufficientPriviledgesException {
		Employee employee = employeeService.getEmployeeById(EmployeeService.getIdFromAuth(auth.getName()));
		comment.setCommentBy(employee);
		comment.setDatetime(new Timestamp(new java.util.Date().getTime()));
		comment.getTicket().setStatus(comment.getStatusTo());
		comment.setStatusFrom(ticketService.getById(comment.getTicket().getId()).getStatus());
		ticketService.update(comment.getTicket(), auth);
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

	@Override
	public Iterable<Comment> getByTicketId(int ticketId) {
		return commentRepository.getByTicketId(ticketId);
	}
}
