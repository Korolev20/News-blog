package com.newsfeed.dao;
import com.newsfeed.models.News;
import com.newsfeed.models.NewsCategory;
import com.newsfeed.services.NewsServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mainDao")
public class NewsDao extends HibernateAbstract<News> {

    @Autowired
    private NewsCategoryDao newsCategoryDao;

    public NewsDao() {
    }

    public List<News> userForOnePage(int page) {

        int newsOnPage = NewsServices.newsOnPage;
        String query = "SELECT * FROM news ORDER BY news.createddate DESC limit " + newsOnPage + " offset " + (page-1)*newsOnPage;
        return (List<News>) getSession().createSQLQuery(query).addEntity(News.class).list();
    }

    public void updateNews(News news,String newsCategoryTitle) {
        getSession().beginTransaction();
        NewsCategory newsCategory = newsCategoryDao.getNewsCategoryByTitle(newsCategoryTitle);
        news.setNewsCategory(newsCategory);
        newsCategory.getSetNews().add(news);
        getSession().merge(newsCategory);
        getSession().merge(news);

    }

    public List<News> newsByCategory(String category) {
        String query = "SELECT * FROM news ns INNER JOIN news_category nscat ON (ns.newsCategory_id=nscat.id) WHERE nscat.title ='"
                +category+"' ORDER BY ns.createddate DESC";

        return (List<News>) getSession().createSQLQuery(query).addEntity(News.class).list();
    }


    public void addNews(News news, String newsCategoryTitle) {
        getSession().beginTransaction();
        NewsCategory newsCategory = newsCategoryDao.getNewsCategoryByTitle(newsCategoryTitle);
        news.setNewsCategory(newsCategory);
        newsCategory.getSetNews().add(news);
        getSession().merge(newsCategory);
        getSession().merge(news);

    }

    public void deleteNews(long id) {
        getSession().delete(getById(id));
    }

    public List<News> searchNewsByTitle(String title) {
        String query = "select * from news where title like '%"+title+"%'";
        return getSession().createSQLQuery(query).addEntity(News.class).list();
    }

    public List<News> searchNewsByDescription(String description) {
        String query = "select * from news where description like '%"+description+"%'";
        return getSession().createSQLQuery(query).addEntity(News.class).list();
    }
}