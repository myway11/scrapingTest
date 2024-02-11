package scrapingTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String baseUrl = "https://scraping-for-beginner.herokuapp.com/";
		String loginUrl;
		String rankingUrl;
		String imagesUrl;

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		driver.get(baseUrl);
		WebElement element = driver.findElement(By.className("card-action"));
		List<WebElement> linkList = element.findElements(By.tagName("a"));
		loginUrl = linkList.get(0).getAttribute("href");
		rankingUrl = linkList.get(1).getAttribute("href");
		imagesUrl = linkList.get(2).getAttribute("href");
		//ログイン
		driver.get(loginUrl);
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("imanishi");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("kohei");
		driver.findElement(By.id("login-btn")).click();
		List<WebElement> tdList = driver.findElements(By.tagName("td"));
		UserData userData = new UserData(tdList);
		//ランキング
		List<RankingData> ranking = new ArrayList<RankingData>();
		driver.get(rankingUrl);
		List<WebElement> rankList = driver.findElements(By.className("u_areaListRankingBox"));
		rankList.forEach(rank -> {
			RankingData rankData = new RankingData();
			WebElement data = rank.findElement(By.tagName("h2"));
			String[] a = data.getText().split("\n");
			rankData.setRank(Integer.parseInt(a[0]));
			rankData.setSpot(a[1]);

			WebElement review = rank.findElement(By.className("evaluateNumber"));
			rankData.setReview(Double.parseDouble(review.getText()));
			ranking.add(rankData);
		});
		System.out.println(ranking);
		driver.quit();

	}

}
