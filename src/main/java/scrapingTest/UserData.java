package scrapingTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebElement;

import lombok.Data;

@Data
public class UserData {
	private String name;
	private String company;
	private LocalDate birthday;
	private String from;
	private String hobby;

	public UserData(List<WebElement> tdList) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy年M月dd日");
		this.name = tdList.get(0).getText();
		this.company = tdList.get(1).getText();
		this.birthday = LocalDate.parse(tdList.get(2).getText(), fmt);
		this.from = tdList.get(3).getText();
		this.hobby = tdList.get(4).getText();
	}
}
