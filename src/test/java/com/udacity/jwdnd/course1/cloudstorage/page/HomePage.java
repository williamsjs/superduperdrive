package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(css="#logout")
    private WebElement logoutForm;

    @FindBy(css="#nav-notes-tab")
    private WebElement notesTab;

    @FindBy(css="#nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(css="#add-note")
    private WebElement addNoteButton;

    @FindBy(css="#add-credential")
    private WebElement addCredentialButton;

    @FindBy(css="#credential-url")
    private WebElement credentialUrlInput;

    @FindBy(css="#credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(css="#credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(css="#save-credential-changes")
    private WebElement credentialSubmit;

    @FindBy(css=".note-title")
    private WebElement noteTitle;

    @FindBy(css="#note-title")
    private WebElement noteTitleInput;

    @FindBy(css="#note-description")
    private WebElement noteDescriptionInput;

    @FindBy(css="#save-note-changes")
    private WebElement noteSubmit;


    @FindBy(css=".credential-username")
    private WebElement credentialUsername;

    @FindBy(css=".credential-password")
    private WebElement credentialPassword;

    @FindBy(css=".btn-danger")
    private WebElement deleteButton;

    @FindBy(css=".btn-success")
    private WebElement editButton;

    @FindBy(css="#close-credential-modal")
    private WebElement credentialModalCloseButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        logoutForm.submit();
    }

    public String addNote(WebDriver driver) {
        notesTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNoteButton));

        addNoteButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteTitleInput));

        noteTitleInput.sendKeys("Title Example");
        noteDescriptionInput.sendKeys("Description Example");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("save-note-changes")));

        noteSubmit.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(noteTitle));
        return noteTitle.getText();
    }

    public String editNote(WebDriver driver) {
        notesTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(noteTitleInput));

        noteTitleInput.clear();
        noteDescriptionInput.clear();
        noteTitleInput.sendKeys("Title Example Edited");
        noteDescriptionInput.sendKeys("Description Example Edited");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("save-note-changes")));

        noteSubmit.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(noteTitle));
        return noteTitle.getText();
    }

    public boolean deleteNote(WebDriver driver) {
        notesTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        try {
            return noteTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public String addCredential(WebDriver driver) {
        credentialsTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));

        addCredentialButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialUrlInput));

        credentialUrlInput.sendKeys("google.com");
        credentialUsernameInput.sendKeys("testusername");
        credentialPasswordInput.sendKeys("badpassword");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialSubmit));

        credentialSubmit.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(credentialUsername));
        return credentialUsername.getText();
    }

    public String editCredential(WebDriver driver) {
        credentialsTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialUrlInput));

        credentialUrlInput.clear();
        credentialUsernameInput.clear();
        credentialPasswordInput.clear();
        credentialUrlInput.sendKeys("facebook.com");
        credentialUsernameInput.sendKeys("facebookusername");
        credentialPasswordInput.sendKeys("facebookpassword");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialSubmit));

        credentialSubmit.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(credentialUsername));
        return credentialUsername.getText();
    }

    public String checkPassword(WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialPasswordInput));

        return credentialPasswordInput.getAttribute("value");
    }

    public String checkHashedPassword() {
        return credentialPassword.getText();
    }

    public void closeModal() {
        credentialModalCloseButton.click();
    }

    public boolean deleteCredential(WebDriver driver) {
        credentialsTab.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        try {
            return credentialUsername.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
