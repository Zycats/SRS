package com.zycats.srs.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	@Query("select c from Comment c where c.ticket.id = :id")
	public Iterable<Comment> getByTicketId(@Param("id") int id);

}
