package com.fu.demo.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fu.demo.common.api.AppException;
import com.fu.demo.common.api.ConstMessage;
import com.fu.demo.mbg.dto.ArticleDto;
import com.fu.demo.mbg.dto.CommentResponseDto;
import com.fu.demo.mbg.dto.CreateArticleDto;
import com.fu.demo.mbg.dto.PageWrapper;
import com.fu.demo.mbg.dto.TitleResponseDto;
import com.fu.demo.mbg.dto.UpdateArticleDto;
import com.fu.demo.mbg.mapper.ArticleMapper;
import com.fu.demo.mbg.mapper.CommentMapper;
import com.fu.demo.mbg.mapper.UserArticleThumbMapper;
import com.fu.demo.mbg.model.Article;
import com.fu.demo.mbg.model.Comment;
import com.fu.demo.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		PageHelper.startPage(1, 3);

		return articleMapper.queryAllArticle();
	}

	@Override
	public PageWrapper<List<TitleResponseDto>> getTitlesByUser(long userId, int page) {
		final int ITEM_PER_PAGE = 4;

		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<TitleResponseDto> titles = articleMapper.queryTitlesByUser(userId);
		PageInfo<TitleResponseDto> pageInfo = new PageInfo<TitleResponseDto>(titles);

		PageWrapper<List<TitleResponseDto>> wrapper = new PageWrapper<List<TitleResponseDto>>();
		wrapper.setContent(titles);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());

		return wrapper;
	}

	@Override
	public PageWrapper<List<TitleResponseDto>> listAllArticle(long userId, int page) {
		final int ITEM_PER_PAGE = 4;

		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<TitleResponseDto> titles = articleMapper.queryAllTitles();
		PageInfo<TitleResponseDto> pageInfo = new PageInfo<TitleResponseDto>(titles);

		PageWrapper<List<TitleResponseDto>> wrapper = new PageWrapper<List<TitleResponseDto>>();
		wrapper.setContent(titles);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());

		return wrapper;
	}

	@Override
	public PageWrapper<List<TitleResponseDto>> getTitleByTag(String tag, int page) {
		final int ITEM_PER_PAGE = 4;

		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<TitleResponseDto> titles = articleMapper.queryTitlesByTag(tag);
		PageInfo<TitleResponseDto> pageInfo = new PageInfo<TitleResponseDto>(titles);

		PageWrapper<List<TitleResponseDto>> wrapper = new PageWrapper<List<TitleResponseDto>>();
		wrapper.setContent(titles);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());

		return wrapper;
	}

	@Override
	public void deleteArticleTag(long articleId, String tag, long userId) {
		ArticleDto articleDto = articleMapper.queryById(articleId);
		if (userId != articleDto.getAuthorId()) {
			throw new AppException(ConstMessage.NOT_OWNER);
		}

		long count = articleMapper.deleteArticleTag(articleId, tag);
		if (count < 1) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
	}

	@Override
	public void addArticleTag(long articleId, String tag) {
		long count = articleMapper.insertArticleTag(articleId, tag);

		if (count != 1) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
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
	public void updateArticle(UpdateArticleDto updateArticleDto, long userId) {
		long id = updateArticleDto.getId();
		long authorId = articleMapper.queryAuthorId(id);

		if (userId != authorId) {
			throw new AppException(ConstMessage.NOT_OWNER);
		}

		String title = updateArticleDto.getTitle();
		String content = updateArticleDto.getContent();

		articleMapper.updateArticle(id, title, content);
	}

	@Override
	public void deleteArticle(long id, long userId) {
		ArticleDto articleDto = articleMapper.queryById(id);
		if (userId != articleDto.getAuthorId()) {
			throw new AppException(ConstMessage.NOT_OWNER);
		}

		int count = articleMapper.delete(id);
		
		if(count<1) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
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
	public PageWrapper<List<CommentResponseDto>> getArticleComments(long articleId, long userId, boolean isSortByDate,
			int page) {
		String sort_param = "articleCommentDate";
		if (false == isSortByDate) {
			sort_param = "articleCommentThumbNum";
		}

		final int ITEM_PER_PAGE = 4;
		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<CommentResponseDto> comments = commentMapper.queryCommentByArticleId(articleId, sort_param);
		PageInfo<CommentResponseDto> pageInfo = new PageInfo<CommentResponseDto>(comments);

		Iterator<CommentResponseDto> iter = comments.iterator();

		while (iter.hasNext()) {
			CommentResponseDto comment = iter.next();

			boolean isThumbed = commentMapper.isThumbed(comment.getId(), userId);
			comment.setThumbState(isThumbed);

//			long thumbNum =commentMapper.queryThumbNum(comment.getId());
//			comment.setThumbNum(thumbNum);
		}

		PageWrapper<List<CommentResponseDto>> wrapper = new PageWrapper<List<CommentResponseDto>>();
		wrapper.setContent(comments);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());

		return wrapper;
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

	@Override
	public PageWrapper<List<TitleResponseDto>> getBookmarkedArticles(long userId, int page) {

		final int ITEM_PER_PAGE = 4;

		PageHelper.startPage(page, ITEM_PER_PAGE);
		List<TitleResponseDto> titles = articleMapper.queryBookmarkedArticle(userId);

		PageInfo<TitleResponseDto> pageInfo = new PageInfo<TitleResponseDto>(titles);
		PageWrapper<List<TitleResponseDto>> wrapper = new PageWrapper<List<TitleResponseDto>>();

		wrapper.setContent(titles);
		wrapper.setPages(pageInfo.getPages());
		wrapper.setPageNum(pageInfo.getPageNum());

		return wrapper;
	}

	@Override
	public void thumbComment(long commentId, long userId) {
		long num = commentMapper.thumb(commentId, userId);

		if (num <= 0) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
		
		commentMapper.incThumb(commentId);
	}

	@Override
	public void unthumbComment(long commentId, long userId) {
		long num = commentMapper.unThumb(commentId, userId);

		if (num <= 0) {
			throw new AppException(ConstMessage.ACTION_NOT_DONE);
		}
		
		commentMapper.decThumb(commentId);
	}
}
