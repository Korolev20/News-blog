package com.newsfeed.services;


import com.newsfeed.dao.NewsCategoryDao;
import com.newsfeed.models.NewsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value="newsCategoryService")
@Transactional
public class NewsCategoryService {

    @Autowired
    private NewsCategoryDao newsCategoryDao;

    public NewsCategoryService() {
    }

    public List<NewsCategory> getAll(){
        return newsCategoryDao.getAll();
    }

    public void addNewsCategory(NewsCategory newsCategory) {
        newsCategoryDao.addNewsCategory(newsCategory);
    }

    public boolean isContained(NewsCategory newsCategory){
        boolean contains = true;
        for(NewsCategory newsCategory1 : getAll()){
            if(newsCategory1.getTitle().equals(newsCategory.getTitle()))
                contains = false;
        }
        return  contains;
    }
    public void deleteNewsCategory(long id) {
        newsCategoryDao.deleteNewsCategory(id);
    }
}