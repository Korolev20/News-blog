package com.newsfeed.services;


import com.newsfeed.dao.NewsDao;
import com.newsfeed.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value="newsServices")
@Transactional
public class NewsServices {

    public static final int newsOnPage = 5;

    @Autowired
    private NewsDao newsDao;

    public NewsServices() {
    }

    public List<News> getAll(){
        return newsDao.getAll();
    }

    public News getById(Long id){
        return newsDao.getById(id);
    }


    public List<News> newsForOnePage(int page) {
        return newsDao.userForOnePage(page);
    }

    public int numberOfPages() {
        int x = newsDao.getAll().size();
        return x % newsOnPage == 0 ? x / newsOnPage : x / newsOnPage + 1;
    }
    public List<News> newsByCategory(String category){
        return newsDao.newsByCategory(category);
    }

    public void updateNews(News news,String newsCategory) {
        newsDao.updateNews(news,newsCategory);
    }

    public void deleteNews(long id) {
        newsDao.deleteNews(id);
    }


    public void addNews(News news, String newsCategory ) {
        newsDao.addNews(news,newsCategory);
    }
    public List<News> searchNewsByTitle(String title) {
        return newsDao.searchNewsByTitle(title);
    }


    public List<News> searchNewsByDescription(String description) {
        return newsDao.searchNewsByDescription(description);
    }

}