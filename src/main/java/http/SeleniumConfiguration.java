/**
 * 
 */
package http;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author MariaMejia
 * @Date Jul 13, 2022
 * 
 * ChromeDriver configuration for a Selenium browser driver
 * NOTE: These configuration settings are specific to my system and version of chrome.
 * 		 This project uses ChromeDriver version number: 103.0.5060.53
 * 
 */
public class SeleniumConfiguration {
	ChromeOptions options;
	
	public SeleniumConfiguration() {
		System.setProperty("webdriver.chrome.driver", "/Users/mariamejia/Desktop/chromedriver");
		this.options = new ChromeOptions();
		options.addArguments("--headless");
	}
	public ChromeDriver driver() {
		return new ChromeDriver(this.options);
	}
}
