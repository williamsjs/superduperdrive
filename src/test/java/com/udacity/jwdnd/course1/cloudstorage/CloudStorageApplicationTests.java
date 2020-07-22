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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private final String username = "Scott";

	private final String password = "password";

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
	@Order(1)
	public void loginLogout() {
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Scott", "Williams", username, password);

		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);
		Assertions.assertEquals("Home", driver.getTitle());

		homePage.logout();

		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(2)
	public void addNote() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertEquals("Title Example", homePage.addNote(driver));
	}

	@Test
	@Order(3)
	public void editNote() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertEquals("Title Example Edited", homePage.editNote(driver));
	}

	@Test
	@Order(4)
	public void deleteNote() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertTrue(homePage.deleteNote(driver));
	}

	@Test
	@Order(5)
	public void addCredential() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertEquals("testusername", homePage.addCredential(driver));
		Assertions.assertEquals("badpassword", homePage.checkPassword(driver));
	}

	@Test
	@Order(6)
	public void editCredential() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertEquals("facebookusername", homePage.editCredential(driver));
		Assertions.assertNotEquals("facebookpassword", homePage.checkHashedPassword());
	}

	@Test
	@Order(7)
	public void deleteCredential() {
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		driver.get(baseURL + "/home");
		HomePage homePage = new HomePage(driver);

		Assertions.assertTrue(homePage.deleteCredential(driver));
	}

}
