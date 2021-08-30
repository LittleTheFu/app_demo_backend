package com.fu.demo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;
import com.fu.demo.mbg.mapper.ArticleMapper;
import com.fu.demo.mbg.mapper.CommentMapper;
import com.fu.demo.mbg.mapper.UserArticleThumbMapper;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.mbg.model.Comment;
import com.fu.demo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserArticleThumbMapper thumbMapper;

	@Override
	public List<ArticleDto> listAllArticle() {
		return articleMapper.queryAllArticle();
	}

	@Override
	public List<ArticleDto> listAllArticle(long userId) {
		List<ArticleDto> articles = articleMapper.queryAllArticle();

		Iterator<ArticleDto> iter = articles.iterator();

		while (iter.hasNext()) {
			ArticleDto article = iter.next();
			
			boolean isThumbed = articleMapper.isThumbed(article.getId(), userId);
			article.setThumbState(isThumbed);
			
			boolean isOwner = (userId == article.getAuthorId());
			article.setDeletable(isOwner);
			article.setEditable(isOwner);
		}

		return articles;
	}

	@Override
	public long createArticle(CreateArticleDto craeteArticleDto, long userId) {
		Article article = new Article();
		BeanUtils.copyProperties(craeteArticleDto, article);
		article.setArticleUserId(userId);

		articleMapper.insert(article);
		
		return article.getId();
	}
	
	@Override
	public boolean updateArticle(UpdateArticleDto updateArticleDto, long userId) {
		long id = updateArticleDto.getId();
		long authorId = articleMapper.queryAuthorId(id);
		
		if(userId != authorId) {
			return false;
		}
		
		String title = updateArticleDto.getTitle();
		String content = updateArticleDto.getContent();
		
		articleMapper.updateArticle(id, title, content);
		
		return true;
	}

	@Override
	public int deleteArticle(long id, long userId) {
		ArticleDto articleDto = articleMapper.queryById(id);
		if(userId != articleDto.getAuthorId()) {
			return 0;
		}
		
		return articleMapper.delete(id);
	}

	@Override
	public void thumb(long articleId, long userId) {
		if (!isThumbed(articleId, userId)) {
			thumbMapper.thumb(articleId, userId);
		}
	}

	@Override
	public void unThumb(long articleId, long userId) {
		if (isThumbed(articleId, userId)) {
			thumbMapper.unThumb(articleId, userId);
		}
	}

	@Override
	public boolean isThumbed(long articleId, long userId) {
		return thumbMapper.getThumbNum(articleId, userId) > 0;
	}

	@Override
	public ArticleDto getArticleById(long id) {
		return articleMapper.queryById(id);
	}

	@Override
	public ArticleDto getArticleById(long id, long userId) {
		ArticleDto article = articleMapper.queryById(id);
		article.setThumbState(articleMapper.isThumbed(id, userId));
		
		boolean isOwner = (userId == article.getAuthorId());
		article.setDeletable(isOwner);
		article.setEditable(isOwner);
		
		boolean isBookmarked = articleMapper.isBookmarked(id, userId);
		article.setBookmarked(isBookmarked);
		
		return article;
	}

	@Override
	public boolean isArticleThumbed(long id, long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getArticleThumbNumber(long id) {
		return articleMapper.queryThumbNumber(id);
	}

	@Override
	public long createComment(long articleId, long userId, String content) {
		Comment comment = new Comment();
		comment.setArticleCommentArticleId(articleId);
		comment.setArticleCommentContent(content);
		comment.setArticleCommentUserId(userId);
		
		commentMapper.insertComment(comment);
		
		return comment.getId();
	}
	
	@Override
	public List<CommentResponseDto> getArticleComments(long articleId) {
		return commentMapper.queryCommentByArticleId(articleId);
	}

	@Override
	public CommentResponseDto getArticleCommentById(long commentId) {
		return commentMapper.queryById(commentId);
	}

	@Override
	public void bookmark(long articleId, long userId) {
		articleMapper.bookmark(articleId, userId);
	}

	@Override
	public void unBookmark(long articleId, long userId) {
		articleMapper.unBookmark(articleId, userId);
	}
}
