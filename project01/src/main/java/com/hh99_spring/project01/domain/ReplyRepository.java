package com.hh99_spring.project01.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	// @Query 어노테이션을 활용하여 article_id로 댓글을 조회한다 정렬 기준은 업데이트 일시 기준
	List<Reply> findByArticleIdOrderByModifiredAtDesc(@Param(value = "article_id") Long article_id);
}
