import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SelenideTests {
    @BeforeTest
    public void setUp () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        browserSize = null;
//        holdBrowserOpen = true;
    }
    @Test
    public void checkboxTest () {
        open("http://the-internet.herokuapp.com/checkboxes ");
        $("input[type=checkbox]").click();
        $("input[type=checkbox]").shouldBe(checked);
        ElementsCollection checkBoxes = $("#checkboxes").$$(byTagName("input"));
        for (SelenideElement element:checkBoxes) {
            element.shouldHave(type("checkbox"));
        }
    }
    @Test
    public void dropDownTest () {
        open("http://the-internet.herokuapp.com/dropdown");
        $(By.id("dropdown")).getSelectedOption().shouldHave(text("Please select an option"));
        $("#dropdown").selectOption("Option 2");
        $(By.id("dropdown")).getSelectedOption().shouldHave(text("Option 2"));
    }
    @Test
    public void textBox () {
        open("https://demoqa.com/text-box");
        $(By.id("userName")).setValue("Nuka Mekerishvili");
        $(by("type", "email")).setValue("nukimekerishvili224@gmail.com");
        $(By.xpath("//*[@id=\"currentAddress\"]")).setValue("ssamgori");
        $("#permanentAddress").setValue("samgori");
        $(withText("Submit")).scrollTo().click();
        $$("p").shouldHave(CollectionCondition
                        .exactTexts("Name:Nuka Mekerishvili", "Email:nukimekerishvili224@gmail.com", "Current Address :ssamgori", "Permananet Address :samgori"));
    }

    @AfterTest
            public void close () {
        getWebDriver().quit();
    }
}
