package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void loginLogout() {
		String baseUrl = "http://localhost:" + this.port;

		driver.get(baseUrl + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseUrl + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseUrl + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		String username = "Scott";
		String password = "password";

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Scott", "Williams", username, password);

		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseUrl + "/home");
		HomePage homePage = new HomePage(driver);
		Assertions.assertEquals("Home", driver.getTitle());

		homePage.logout();

		driver.get(baseUrl + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void noteWorkflow() {
		String baseUrl = "http://localhost:" + this.port;

		driver.get(baseUrl + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		String username = "Scott";
		String password = "password";

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Scott", "Williams", username, password);

		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseUrl + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertEquals("Title Example", homePage.addNote(driver));

		Assertions.assertTrue(homePage.deleteNote(driver));
	}

	@Test
	public void credentialWorkflow() {
		String baseUrl = "http://localhost:" + this.port;

		driver.get(baseUrl + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		String username = "Scott";
		String password = "password";

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Scott", "Williams", username, password);

		driver.get(baseUrl + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseUrl + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertNotEquals("badpassword", homePage.addCredential(driver));
		Assertions.assertEquals("badpassword", homePage.checkPassword(driver));

		homePage.closeModal();
		Assertions.assertTrue(homePage.deleteCredential(driver));
	}

}
