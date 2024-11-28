package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x720";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";


    }


    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        //remove ad banners
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //text input
        $("#firstName").setValue("Jane");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("jana@test.com");

        //radio
        $("#gender-radio-1 + .custom-control-label").click();
        $("#userNumber").setValue("1234567890");

        //input autofill on arrow+enter
        $("#subjectsInput").setValue("Chemistry").sendKeys(Keys.DOWN, Keys.RETURN);
        //input autofill on tab
        $("#subjectsInput").setValue("Computer Science").pressTab();

        //checkbox, scroll to view
        $("#hobbies-checkbox-1 + .custom-control-label").click();

        //file upload
        $("#uploadPicture").uploadFile(new File("src/test/resources/test-img.jpg"));

        //textarea
        $("#currentAddress").setValue("17926 Eleanora Islands");

        //dropdowns
        $("#state").click();
        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        //datepicker
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("4");
        $(".react-datepicker__year-select").selectOptionByValue("2014");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        //submit btn
        $("#submit").click();


        //success message for sent form
        $(".modal-dialog .modal-title").shouldHave(text("Thanks for submitting the form"));


        //result table
        //check for value if specific value is provided
        //check if not empty if no specific value is provided

        ElementsCollection resultCellSelector = $$(".table > tbody > tr > td");
        resultCellSelector.findBy(text("Student Name")).sibling(0).shouldHave(text("Jane"));
        resultCellSelector.findBy(text("Student Name")).sibling(0).shouldHave(text("Doe"));
        resultCellSelector.findBy(text("Student Email")).sibling(0).shouldHave(text("jana@test.com"));
        resultCellSelector.findBy(text("Gender")).sibling(0).shouldNotBe(empty);
        resultCellSelector.findBy(text("Mobile")).sibling(0).shouldHave(text("1234567890"));
        resultCellSelector.findBy(text("Date of Birth")).sibling(0).shouldHave(text("04 May,2014"));
        resultCellSelector.findBy(text("Subjects")).sibling(0).shouldHave(text("Chemistry, Computer Science"));
        resultCellSelector.findBy(text("Hobbies")).sibling(0).shouldNotBe(empty);
        resultCellSelector.findBy(text("Picture")).sibling(0).shouldNotBe(empty);
        resultCellSelector.findBy(text("Address")).sibling(0).shouldHave(text("17926 Eleanora Islands"));
        resultCellSelector.findBy(text("State and City")).sibling(0).shouldNotBe(empty);

//      sleep(600_000);
    }
}
