package com.hh99_spring.project01.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	@Query(value = "SELECT * FROM reply r WHERE r.article_id = :article_id order by modified_at desc", nativeQuery = true) 
	List<Reply> findByArticleIdOrderByModifiredAtDesc(@Param(value = "article_id") Long article_id);
}
