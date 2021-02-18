package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BookingSteps {
    String city;
    public static final String URL = "https://www.booking.com/";
    public static final String SEARCH_BOX_BUTTON = ".sb-searchbox__button";
    public static final String NAME_HOTEL = ".sr-hotel__name";

    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @After
    public void quit() {
        WebDriverRunner.getWebDriver().quit();
    }

    @Given("User is looking for {string} city")
    public void userIsLookingForCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        open(URL);
        $(By.id("ss")).sendKeys(city);
        $(SEARCH_BOX_BUTTON).click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        assertThat($$(NAME_HOTEL).texts(), hasItem(hotel));
    }

    @And("Rating of the hotel {string} is {string}")
    public void ratingOfTheHotel(String hotel, String rating) {
        assertThat($$(By.xpath(String.format("//*[contains(@class,'sr-hotel__name')and contains(text(),'%s')]/ancestor::*[contains(@class,'sr_item_content_slider_wrapper')]//*[@class = 'bui-review-score__badge']", hotel))).texts(), hasItem(rating));
    }
}
