package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AdvancedCssSelectorTest extends BaseTest {

    @Test(priority = 1)
    public void testPrefix() {
        // ^= : atribut diawali teks tertentu
        WebElement el = driver.findElement(By.cssSelector("input[id^='session_token_']"));
        Assert.assertEquals(el.getAttribute("value"), "TOKEN-SECURE-ALPHA-77");
    }

    @Test(priority = 2)
    public void testSuffix() {
        // $= : atribut diakhiri teks tertentu
        WebElement el = driver.findElement(By.cssSelector("input[id$='_customer_pin']"));
        Assert.assertEquals(el.getAttribute("value"), "123456");
    }

    @Test(priority = 3)
    public void testContains() {
        // *= : atribut mengandung teks tertentu
        WebElement el = driver.findElement(By.cssSelector("input[name*='_branch_code_']"));
        Assert.assertEquals(el.getAttribute("value"), "JKT-DAANMOGOT-01");
    }

    // ===== Direct child & adjacent sibling =====

    @Test(priority = 4)
    public void testDirectChild() {
        // > : hanya anak langsung
        List<WebElement> items = driver.findElements(By.cssSelector("ul#accountTypeList > li"));
        Assert.assertEquals(items.size(), 3);
    }

    @Test(priority = 5)
    public void testAdjacentSibling() {
        // + : elemen tepat setelah elemen lain (satu level)
        WebElement checkbox = driver.findElement(By.cssSelector("label[for='optNewsletter'] + input"));
        Assert.assertEquals(checkbox.getAttribute("id"), "optNewsletter");
    }

    // ===== Pseudo-classes =====

    @Test(priority = 6)
    public void testDisabled() {
        WebElement el = driver.findElement(By.cssSelector("input:disabled"));
        Assert.assertFalse(el.isEnabled());
        Assert.assertEquals(el.getAttribute("value"), "AKUN DIBLOKIR SEMENTARA");
    }

    @Test(priority = 7)
    public void testChecked() {
        WebElement el = driver.findElement(By.cssSelector("input[type='checkbox']:checked"));
        Assert.assertTrue(el.isSelected());
    }

    @Test(priority = 8)
    public void testFirstChild() {
        String text = driver.findElement(By.cssSelector("#accountTypeList li:first-child")).getText();
        Assert.assertEquals(text, "1. Tabungan Juara Masa Depan (Bunga 5.5% p.a)");
    }

    @Test(priority = 9)
    public void testNthChild() {
        String text = driver.findElement(By.cssSelector("#accountTypeList li:nth-child(2)")).getText();
        Assert.assertEquals(text, "2. Deposito Berjangka Valas USD (Bunga 4.2% p.a)");
    }

    @Test(priority = 10)
    public void testLastChild() {
        String text = driver.findElement(By.cssSelector("#accountTypeList li:last-child")).getText();
        Assert.assertEquals(text, "3. Giro Usaha Fleksibel Bebas Biaya Admin");
    }

}