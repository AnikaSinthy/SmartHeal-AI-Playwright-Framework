package com.saucedemo.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.saucedemo.ai.AIService;

public class BasePage {
    protected Page page;

    public BasePage(Page page) { this.page = page; }

    public void smartClick(String selector) {
        try {
            page.click(selector, new Page.ClickOptions().setTimeout(2500));
        } catch (PlaywrightException e) {
            System.out.println("AI Healing active for: " + selector);
            String healed = AIService.getHealedSelector(page.content(), selector);
            page.click(healed);
        }
    }

    public void smartFill(String selector, String text) {
        try {
            page.fill(selector, text, new Page.FillOptions().setTimeout(2500));
        } catch (PlaywrightException e) {
            String healed = AIService.getHealedSelector(page.content(), selector);
            page.fill(healed, text);
        }
    }
}