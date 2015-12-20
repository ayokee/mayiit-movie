package com.mayiit.movie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayiit.movie.model.Movie;
import com.mayiit.movie.service.IMovieService;

@Controller
public class IndexAction {
	
	@Autowired
	private IMovieService iMovieService;
	

	/**首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index.htm")
	public String index(HttpServletRequest request,Map<String, Object> model){
		String name = request.getParameter("name");
		System.out.println("-----");
		
		int limitMin=0;
		int limitMax=8;
	/****************************影讯&购票*****************************/	
		
		
/*		List<Navigation> navigationList = iNavigationService.findAll(navigationMap);
		model.put("navigationList",navigationList);*/
	/****************************影讯&购票*****************************/
		
		
	/****************************分          类*****************************/
	/****************************分          类*****************************/
		
		
	/***************************电影，电视剧****************************/
/*		Map<String,Object> movie1Map = new HashMap<>();
		List<Movie> movieAndTV = iMovieService.findAllMovieAndTVForIndex(movie1Map);
		model.put("movieAndTV",movieAndTV);*/
	/***************************电影，电视剧****************************/
		Map<String,Object> movie1Map = new HashMap<String,Object>();
		movie1Map.put("subtype", 0);
		movie1Map.put("limitMin", limitMin);
		movie1Map.put("limitMax", 4);
		List<Movie> movies = iMovieService.findAllMovieForIndex(movie1Map);
		
		movie1Map.put("subtype", 1);
		movie1Map.put("limitMin", limitMin);
		movie1Map.put("limitMax", 4);
		
		List<Movie> tvs = iMovieService.findAllTVForIndex(movie1Map);
		model.put("movies",movies);
		model.put("tvs",tvs);
	/****************************电          影*****************************/
		Map<String,Object> movie2Map = new HashMap<String,Object>();

		movie2Map.put("subtype", 0);
		movie2Map.put("limitMin", limitMin);
		movie2Map.put("limitMax", limitMax);
		
		List<Movie> movie = iMovieService.findAllMovieForIndex(movie2Map);
		model.put("movie",movie);
	/****************************电          影*****************************/	
		
		
	/****************************电   视   剧*****************************/
		movie2Map.put("subtype", 1);
		movie2Map.put("limitMin", limitMin);
		movie2Map.put("limitMax", limitMax);
		
		List<Movie> tv = iMovieService.findAllTVForIndex(movie2Map);
		model.put("tv",tv);
	/****************************电   视   剧*****************************/	
		
		model.put("name","&name=" + name);
		return "/index/movieIndex";
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "movieDetails.htm")
	public String movieDetails(HttpServletRequest request,Map<String, Object> model){
		String id = request.getParameter("id");
		
		Movie movie=null;
		if(id!=null&&!"".equals(id)){
			movie = iMovieService.findMovieDetailById(Integer.parseInt(id));
		}
		List<String> list = new LinkedList<String>();
		if(movie!=null){
			String photos = movie.getPhotos();
			String photo[] = photos.split(",");
			list=Arrays.asList(photo);
		}
		model.put("movie", movie);
		model.put("phtots", list);
		return "/details/movie-details";
	}
	
	
}
