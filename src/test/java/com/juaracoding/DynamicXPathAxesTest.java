package com.juaracoding;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicXPathAxesTest extends BaseTest {

    @Test(priority = 1)
    public void testNormalizeSpace() {
        // Teks tombol punya spasi/enter berlebih, normalize-space() merapikannya
        driver.findElement(By.xpath("//button[normalize-space()='Konfirmasi Transaksi Perbankan']")).click();
        String feedback = driver.findElement(By.id("actionFeedback")).getText();
        Assert.assertEquals(feedback, "Aksi [Konfirmasi] berhasil diproses untuk Semua Rekening");
    }

    @Test(priority = 2)
    public void testContainsText() {
        String name = driver.findElement(By.xpath("//td[contains(text(),'Budi')]")).getText();
        Assert.assertEquals(name, "Budi Santoso");
    }

    @Test(priority = 3)
    public void testStartsWith() {
        int jumlah = driver.findElements(By.xpath("//td[starts-with(text(),'ACC-')]")).size();
        Assert.assertEquals(jumlah, 3);
    }

    // ===== Kondisi boolean =====

    @Test(priority = 4)
    public void testAnd() {
        int jumlah = driver.findElements(
                By.xpath("//button[contains(@class,'btn-danger') and contains(@class,'btn-action-block')]")).size();
        Assert.assertEquals(jumlah, 3);
    }

    @Test(priority = 5)
    public void testOr() {
        int jumlah = driver.findElements(
                By.xpath("//button[text()='Transfer' or text()='Aktivasi']")).size();
        Assert.assertEquals(jumlah, 3);
    }

    @Test(priority = 6)
    public void testNot() {
        // Baris yang TIDAK berstatus suspended
        int jumlah = driver.findElements(
                By.xpath("//tbody/tr[not(.//span[contains(@class,'badge-suspended')])]")).size();
        Assert.assertEquals(jumlah, 2);
    }

    // ===== XPath Axes =====

    @Test(priority = 7)
    public void testFollowingSiblingClick() {
        // Dari nama nasabah -> ke sel aksi di sebelah kanan -> tombol Transfer
        driver.findElement(By.xpath(
                "//td[text()='Siti Nurhaliza']/following-sibling::td[@class='col-actions']"
                + "/button[contains(@class,'btn-action-transfer')]")).click();
        String feedback = driver.findElement(By.id("actionFeedback")).getText();
        Assert.assertEquals(feedback, "Aksi [Transfer] berhasil diproses untuk Siti Nurhaliza (ACC-1002)");
    }

    @Test(priority = 8)
    public void testFollowingSiblingText() {
        String saldo = driver.findElement(By.xpath(
                "//td[text()='Budi Santoso']/following-sibling::td[@class='col-balance']")).getText();
        Assert.assertEquals(saldo, "Rp 50.000.000");
    }

    @Test(priority = 9)
    public void testAncestor() {
        // Dari tombol Aktivasi -> naik ke baris (tr) induknya
        String idBaris = driver.findElement(By.xpath(
                "//button[contains(@class,'btn-action-activate')]/ancestor::tr")).getAttribute("id");
        Assert.assertEquals(idBaris, "row-acc-1003");
    }

    @Test(priority = 10)
    public void testPrecedingSibling() {
        // Dari nama -> sel tepat di sebelah kirinya (No Rekening)
        String noRek = driver.findElement(By.xpath(
                "//td[text()='Andi Wijaya']/preceding-sibling::td[1]")).getText();
        Assert.assertEquals(noRek, "ACC-1003");
    }

    @Test(priority = 11)
    public void testClickByRowCondition() {
        // Cari baris berdasarkan No Rekening, lalu klik tombol Blokir di baris itu
        driver.findElement(By.xpath(
                "//tr[td[text()='ACC-1001']]//button[contains(@class,'btn-action-block')]")).click();
        String feedback = driver.findElement(By.id("actionFeedback")).getText();
        Assert.assertEquals(feedback, "Aksi [Blokir] berhasil diproses untuk Budi Santoso (ACC-1001)");
    }

}