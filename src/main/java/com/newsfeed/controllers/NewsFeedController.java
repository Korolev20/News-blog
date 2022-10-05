package com.newsfeed.controllers;

import com.newsfeed.models.News;
import com.newsfeed.models.NewsCategory;
import com.newsfeed.services.NewsCategoryService;
import com.newsfeed.services.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewsFeedController {
    @Autowired
    NewsServices newsServices;
    @Autowired
    NewsCategoryService newsCategoryService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String start(@ModelAttribute News news, BindingResult result, ModelMap model) {
        int pages = newsServices.numberOfPages();
        model.addAttribute("newsList", newsServices.newsForOnePage(1));
        model.addAttribute("pages", pages);
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());

        return "index";
    }

    @RequestMapping(value = {"newsByCategory"}, method = RequestMethod.GET)
    public String getByCategory(ModelMap model, @RequestParam("category") String category) {
        model.addAttribute("newsList", newsServices.newsByCategory(category));
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());
        return "Category";
    }

    @RequestMapping(value = {"delete"}, method = RequestMethod.GET)
    public String delete(ModelMap model, @RequestParam("id") String id) {
        newsServices.deleteNews(Long.parseLong(id));
        return "redirect:/";
    }

    @RequestMapping(value = {"deleteCategory"}, method = RequestMethod.GET)
    public String deleteCategory(ModelMap model, @RequestParam("id") String id) {
        newsCategoryService.deleteNewsCategory(Long.parseLong(id));
        return "redirect:/";
    }
    @RequestMapping(value = {"pages"}, method = RequestMethod.GET)
    public String paging(@ModelAttribute News news, BindingResult result, ModelMap model, @RequestParam("page") String page) {
        int nPage;
        if (page == null || page.length() == 0) {
            nPage = 1;
        } else {
            nPage = Integer.parseInt(page);
        }
        List<News> newsList = newsServices.newsForOnePage(nPage);
        int pages = newsServices.numberOfPages();
        model.addAttribute("newsList", newsList);
        model.addAttribute("pages", pages);
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());
        return "index";
    }

    @RequestMapping(value = {"addCategory"}, method = RequestMethod.GET)
    public String addCategory(@ModelAttribute("newsCategory") NewsCategory newsCategory, ModelMap model, String category) {
        return "addCategory";
    }

    @RequestMapping(value = {"addCategory"}, method = RequestMethod.POST)
    public String addedCategory(ModelMap model, @ModelAttribute NewsCategory newsCategory) {
        if(newsCategoryService.isContained(newsCategory)){
            newsCategoryService.addNewsCategory(newsCategory);
            return "redirect:/";
        }
        return "alert";
    }

    @RequestMapping(value = {"addNews"}, method = RequestMethod.GET)
    public String add(@ModelAttribute("news") News news, ModelMap model, String category) {
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());
        return "addNews";
    }

    @RequestMapping(value = {"addNews"}, method = RequestMethod.POST)
    public String added(ModelMap model, @ModelAttribute News news, @RequestParam("category") String category,@RequestParam("text") String text,@RequestParam("date") String date) {
        text = text.replace("\n  ","<br/>&nbsp;&nbsp;&nbsp;&nbsp;").replace("\n","<br/>");
        news.setDescription(text);
        newsServices.addNews(news, category);
        return "redirect:/";
    }

    @RequestMapping(value = {"editNews"}, method = RequestMethod.GET)
    public String edit(ModelMap model, @RequestParam("id") String id) {
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());
        News news = newsServices.getById(Long.parseLong(id));
        String text = news.getDescription().replace("<br/>&nbsp;&nbsp;&nbsp;&nbsp;","\n  ").replace("<br/>","\n");
        news.setDescription(text);
        model.addAttribute("news", news);
        return "editNews";
    }

    @RequestMapping(value = {"editNews"}, method = RequestMethod.POST)
    public String edited(ModelMap model, @ModelAttribute("news") News news, @RequestParam("category") String category,@RequestParam("text") String text) {
        text = text.replace("\n  ","<br/>&nbsp;&nbsp;&nbsp;&nbsp;").replace("\t","  ").replace("\n","<br/>");
        news.setDescription(text);
        newsServices.updateNews(news, category);

        return "redirect:/";
    }
    @RequestMapping(value = {"open"}, method = RequestMethod.GET)
    public String open(ModelMap model, @RequestParam("id") String id) {
        News news = newsServices.getById(Long.parseLong(id));
        model.addAttribute("news", news);
        return "open";
    }


    @RequestMapping(value = {"search"}, method = RequestMethod.POST)
    public String search(@ModelAttribute News news, BindingResult result, ModelMap model, @RequestParam(value ="TitleSearching",required = false) String TitleSearching,
                         @RequestParam(value = "DescriptionSearching", required=false) String DescriptionSearching,@RequestParam("string") String string) {

        List<News> newsList = null;

        if(DescriptionSearching==null||TitleSearching==null){
            if(DescriptionSearching==null){
                newsList = newsServices.searchNewsByTitle(string);
            }
            else{
                newsList = newsServices.searchNewsByDescription(string);
            }
        }
        else{
            newsList= newsServices.searchNewsByTitle(string);
            List<News> byDescription = (newsServices.searchNewsByDescription(string));
            newsList.addAll(byDescription);
        }
        model.addAttribute("newsCategoryList", newsCategoryService.getAll());
        model.addAttribute("newsList", newsList);
        return "index";
    }

}