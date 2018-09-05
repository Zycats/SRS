package com.zycats.srs.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycats.srs.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
