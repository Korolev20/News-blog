package com.newsfeed.dao;

import com.newsfeed.models.NewsCategory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "newsCategoryDao")
public class NewsCategoryDao extends HibernateAbstract<NewsCategory>{

    public NewsCategoryDao() {
    }

    @Override
    public List<NewsCategory> getAll() {
        String query = "select * from news_category order by title";
        return (List<NewsCategory>) getSession().createSQLQuery(query).addEntity(NewsCategory.class).list();
    }

    public NewsCategory getNewsCategoryByTitle(String title){
        NewsCategory searched = null;
        List<NewsCategory> newsCategoryList = getAll();
        for(NewsCategory newsCategory:newsCategoryList){
            if(newsCategory.getTitle().equals(title)){
                searched = newsCategory;
                break;
            }
        }
        return searched;
    }
    public void addNewsCategory(NewsCategory newsCategory) {
        getSession().beginTransaction();
        getSession().merge(newsCategory);
    }
    public void deleteNewsCategory(long id) {
        getSession().delete(getById(id));
    }
}