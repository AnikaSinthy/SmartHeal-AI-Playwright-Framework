package com.saucedemo.tests;

import com.microsoft.playwright.*;
import com.saucedemo.ai.AIService;
import com.saucedemo.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.*;

public class FullFlowTest {
    Playwright playwright;
    Browser browser;
    Page page;
    StorePage storePage;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        storePage = new StorePage(page);
    }

    @Test(description = "Advanced E2E Flow with AI Self-Healing and UI Audit")
    public void testProfessionalFlow() {
        storePage.navigate()
                .addItemToCart()
                .goToCheckout();

        boolean isUIValid = AIService.auditUI(page.content(), "The checkout page should show shipping fields");
        System.out.println("AI Visual Audit Result: " + (isUIValid ? "Professional" : "Review Needed"));

        storePage.fillCheckout("candidate@test.com", "Expert", "123 Tech Lane");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}