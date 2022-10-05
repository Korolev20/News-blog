package com.newsfeed.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="news_category")
public class NewsCategory extends Model{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column(name="title",length = 50)
    private String title;

    @OneToMany(mappedBy = "newsCategory",orphanRemoval = true)
    private Set<News> setNews = new HashSet<>();

    public NewsCategory() {
        super();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<News> getSetNews() {
        return setNews;
    }

    public void setSetNews(Set<News> setNews) {
        this.setNews = setNews;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}