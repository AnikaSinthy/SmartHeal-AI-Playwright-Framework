package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class StorePage extends BasePage {
    public StorePage(Page page) { super(page); }

    public StorePage navigate() {
        page.navigate("https://sauce-demo.myshopify.com/");
        return this;
    }

    public StorePage addItemToCart() {
        smartClick("text=Catalog");
        smartClick(".product-card:first-child a");
        smartClick("button[name='add']");
        page.waitForTimeout(1000);
        return this;
    }

    public StorePage goToCheckout() {
        smartClick("a:has-text('Check out')");
        return this;
    }

    public void fillCheckout(String email, String name, String addr) {
        smartFill("#checkout_email", email);
        smartFill("#checkout_shipping_address_last_name", name);
        smartFill("#checkout_shipping_address_address1", addr);
    }
}