package com.newsfeed.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="news")
public class News extends Model{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column(name = "title",length = 50)
    private String title;

    @Column(name = "description",length = 4096)
    private String description;


    @Column(name = "createdDate", columnDefinition = "timestamp")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "newsCategory_id")
    private NewsCategory newsCategory;

    // method for getting introduction
    public String getIntro(){
        String str = this.description;
        String intro = null;
        if(str==null||str.length()==0)
            return "Description is empty";
        try{
            intro = str.substring(0,500)+"...//";
        }
        catch (Exception e){
            intro = str;
        }
        return intro;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public News() {
        super();
    }



}