/**
 * 
 */
package user;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import http.ScraperService;

/**
 * @author MariaMejia
 * @Date Jul 15, 2022
 * 
 * The main method of this project.
 * A simple command line interface to use when this project is run.
 *  
 */
public class CommandLinePrompter {
	
	public static void main(String[] args) {
		ScraperService scraperService = new ScraperService();
		File file = new File("./src/main/resources/output.txt");
		List<String> topics = scraperService.scrapeTopics();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=============================");
		System.out.println("Browse the Cochrane Library");
		System.out.println("=============================");
		
		topics.forEach(t -> System.out.println(t));
		System.out.println("========================================");
		System.out.println("Please input a topic to browse reviews:");
		System.out.println("========================================");
		final String input = scanner.nextLine().strip().toLowerCase();
		System.out.println("Scanning Cochrane Library...");
		String currentTopic = "";
		
		switch(input) {
			case "allergy & intolerance":
				currentTopic = "Allergy & intolerance";
				break;
			case "blood disorders":
				currentTopic = "Blood disorders";
				break;
			case "cancer":
				currentTopic = "Cancer";
				break;
			case "child health":
				currentTopic = "Child health";
				break;
			case "complementary & alternative medicine":
				currentTopic = "Complementary & alternative medicine";
				break;
			case "consumer & communication strategies":
				currentTopic = "Consumer & communication strategies";
				break;
			case "dentistry & oral health":
				currentTopic = "Dentistry & oral health";
				break;
			case "developmental, psychosocial & learning problems":
				currentTopic = "Developmental, psychosocial & learning problems";
				break;
			case "diagnosis":
				currentTopic = "Diagnosis";
				break;
			case "ear, nose & throat":
				currentTopic = "Ear, nose & throat";
				break;
			case "effective practice & health systems":
				currentTopic = "Effective practice & health systems";
				break;
			case "endocrine & metabolic":
				currentTopic = "Endocrine & metabolic";
				break;
			case "eyes & vision":
				currentTopic = "Eyes & vision";
				break;
			case "gastroenterology & hepatology":
				currentTopic = "Gastroenterology & hepatology";
				break;
			case "genetic disorders":
				currentTopic = "Genetic disorders";
				break;
			case "gynaecology":
				currentTopic = "Gynaecology";
				break;
			case "health & safety at work":
				currentTopic = "Health & safety at work";
				break;
			case "health professional education":
				currentTopic = "Health professional education";
				break;
			case "heart & circulation":
				currentTopic = "Heart & circulation";
				break;
			case "infectious disease":
				currentTopic = "Infectious disease";
				break;
			case "insurance medicine":
				currentTopic = "Insurance medicine";
				break;
			case "kidney disease":
				currentTopic = "Kidney disease";
				break;
			case "lungs & airways":
				currentTopic = "Lungs & airways";
				break;
			case "mental health":
				currentTopic = "Mental health";
				break;
			case "methodology":
				currentTopic = "Methodology";
				break;
			case "neonatal care":
				currentTopic = "Neonatal care";
				break;
			case "neurology":
				currentTopic = "Neurology";
				break;
			case "orthopaedics & trauma":
				currentTopic = "Orthopaedics & trauma";
				break;
			case "pain & anaesthesiapain":
				currentTopic = "Pain & anaesthesia";
				break;
			case "pregnancy & childbirth":
				currentTopic = "Pregnancy & childbirth";
				break;
			case "public health":
				currentTopic = "Public health";
				break;
			case "reproductive & sexual health":
				currentTopic = "Reproductive & sexual health";
				break;
			case "rheumatology":
				currentTopic = "Rheumatology";
				break;
			case "skin disorders":
				currentTopic = "Skin disorders";
				break;
			case "tobacco, drugs & alcohol":
				currentTopic = "Tobacco, drugs & alcohol";
				break;
			case "urology":
				currentTopic = "Urology";
				break;
			case "wounds":
				currentTopic = "Wounds";
				break;
			default:
				currentTopic = ""; //just in case
			
		}
		if (currentTopic == "") System.out.println("The topic name requested is invalid.");
		else {
			try {
				FileUtils.writeLines(file, scraperService.scrapeTopic(currentTopic));
			} catch (IOException e) {
				System.out.println("There was an error writing to the output file.");
				e.printStackTrace();
			}
			System.out.println("Your requested reviews can be found in src/main/resources/output.txt");
		}
		scanner.close();
	}

}
