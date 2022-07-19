/**
 * 
 */
package text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import models.Review;

/**
 * @author MariaMejia
 * @Date Jul 13, 2022
 */
public class ReviewParser {

	private static ReviewParser instance = null;
	private ReviewParser() {}
	public static ReviewParser getInstance() {
		if(instance == null) instance = new ReviewParser();
		return instance;
	}
	
	/*
	 * Parameters: the raw text from html source code, and the topic selected
	 * Returns: a Review object with all relevant data
	 */
	public Review parseRawReviewText(String text, String currentTopic) {
		String[] reviewChunks = text.split("\n");
		Review review = new Review();
		
		review.setTitle(reviewChunks[0]);
		review.setDate(parseDate(reviewChunks[4]));
		review.setAuthor(reviewChunks[1]);
		try {
			review.setEntryURL(new URL(reviewChunks[reviewChunks.length -1]));
		} catch (MalformedURLException e) {
			System.out.println("There was a problem grabbing the review's URL");
			e.printStackTrace();
		}
		review.setTopic(currentTopic);
		
		return review;
	}
	/*
	 * Private helper method to reformat the dates as requested
	 * Parameters: a string in plain English date format (i.e. 2 July 2020)
	 * Returns: a string representing the requested numeric format (i.e. 2020-7-2)
	 * 
	 */
	
	private String parseDate(String date) {
		String[] dateChunks = date.split(" ");
		List<String> months = new ArrayList<String>(List.of("January","February", "March", "April", "May",
			"June", "July", "August", "September", "October","November",
			"December"));
		
		String newDate = dateChunks[2] +"-"+ (months.indexOf(dateChunks[1])+1) +"-"+ dateChunks[0];
		return newDate;
	}
}
