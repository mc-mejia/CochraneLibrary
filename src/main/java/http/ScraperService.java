/**
 * 
 */
package http;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Review;
import text.ReviewParser;

/**
 * @author MariaMejia
 * @Date Jul 13, 2022
 * 
 * A custom web scraper specifically made for cochranelibrary.com
 */
@Data
@AllArgsConstructor
public class ScraperService {

	private static final String URL = "https://www.cochranelibrary.com/";
	private static final String value = "browse-by-topic";
	
	private final ChromeDriver driver;
	private static List<String> topics; 
	
	public ScraperService() {
		this.driver = new SeleniumConfiguration().driver();
		topics = new ArrayList<>();
	}
	
	/*
	 * Returns: a list of topic names
	 */
	public List<String> scrapeTopics(){
		topics = new ArrayList<String>();
		
		this.driver.get(URL + value);
		final List<WebElement> topicsElements = driver.findElementsByClassName("span4");
		for(WebElement topic : topicsElements) {
			List<WebElement> topicList = topic.findElements(By.tagName("a"));
			topicList.forEach(t -> topics.add(t.getText()));

		}
		return topics;
	}

	/*
	 * Parameters: a valid topic name, case sensitive
	 * Returns: a list of review objects within the given topic
	 */
	public List<Review> scrapeTopic(String name) {
		
		this.driver.get(URL + value);
		final List<WebElement> topicsElements = driver.findElementsByClassName("span4");
		
		for(WebElement topic : topicsElements) {
			List<WebElement> topicList = topic.findElements(By.tagName("a"));
			for(WebElement t: topicList) {
				if(t.getText().contains(name)) {
					try {
						t.click();
						driver.getPageSource();
						return scrapeReviews(name);
					}catch(StaleElementReferenceException e) {
						driver.getPageSource();
					}			
				}	
			}
		}
		return null;
	}
	
	/*
	 * Private Helper Method
	 * 
	 * Parameters: a valid topic name, case sensitive
	 * Returns: a list of review objects within the given topic
	 */
	private List<Review> scrapeReviews(String topicName) {
		ReviewParser textParser = ReviewParser.getInstance();
		final List<WebElement> reviewElements = driver.findElementsByClassName("search-results");
		List<Review> reviews = new ArrayList<>();
		
		for(WebElement review : reviewElements) {
			List<WebElement> reviewElementList = review.findElements(By.className("search-results-item-body"));
			//Pass the raw HTML text to our text package's ReviewParser
			reviewElementList.forEach(w -> reviews.add(
					textParser.parseRawReviewText(w.getText() + "\n" + w.findElement(By.tagName("a")).getAttribute("href")+ "\n", topicName)
					)
			); 
		}
		this.driver.quit();
		return reviews;
		
	}
	
}
