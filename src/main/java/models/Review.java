/**
 * 
 */
package models;

import java.io.Serializable;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MariaMejia
 * @Date Jul 13, 2022
 * 
 * A representation of all requested data to be scraped from CochraneLibrary.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable{
	private static final long serialVersionUID = 1L;
	private URL entryURL;
	private String topic;
	private String title;
	private String author;
	private String date;
	
	public String toString() {
		return entryURL + " | " + topic + " | " + title + " | " + author + " | " + date;
	}
}
