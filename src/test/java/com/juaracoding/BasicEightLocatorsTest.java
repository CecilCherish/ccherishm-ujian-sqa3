package com.juaracoding;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicEightLocatorsTest extends BaseTest {

    @Test(priority = 1)
    public void testById() {
        String value = driver.findElement(By.id("txtCustomerCode")).getAttribute("value");
        Assert.assertEquals(value, "CUST-REG-2026-9901");
    }

    @Test(priority = 2)
    public void testByName() {
        String value = driver.findElement(By.name("customerEmail")).getAttribute("value");
        Assert.assertEquals(value, "nasabah.prioritas@juaracoding.com");
    }

    @Test(priority = 3)
    public void testByClassName() {
        String text = driver.findElement(By.className("badge-vip")).getText();
        Assert.assertEquals(text, "Nasabah Prioritas VIP Gold");
    }

    @Test(priority = 4)
    public void testByTagName() {
        String text = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(text, "Portal Simulasi Rekening Nasabah JuaraCoding");
    }

    @Test(priority = 5)
    public void testByLinkText() {
        boolean tampil = driver.findElement(By.linkText("Syarat dan Ketentuan Layanan Perbankan")).isDisplayed();
        Assert.assertTrue(tampil);
    }

    @Test(priority = 6)
    public void testByPartialLinkText() {
        boolean tampil = driver.findElement(By.partialLinkText("Syarat dan Ketentuan")).isDisplayed();
        Assert.assertTrue(tampil);
    }

    @Test(priority = 7)
    public void testByCssSelector() {
        String text = driver.findElement(By.cssSelector("button.btn-submit")).getText();
        Assert.assertEquals(text, "Daftarkan Data Nasabah");
    }

    @Test(priority = 8)
    public void testByXPath() {
        driver.findElement(By.xpath("//button[@id='btnSubmitRegistration']")).click();
        String feedback = driver.findElement(By.id("actionFeedback")).getText();
        Assert.assertEquals(feedback,
                "Aksi [Pendaftaran] berhasil diproses untuk Nasabah Baru Terdaftar (REG-OK)");
    }

}