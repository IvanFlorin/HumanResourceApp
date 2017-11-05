package io.zipcoder.threedaystodeliver.humanresourceapp;

import org.junit.Assert;
import org.junit.Test;


public class StreetAddressTest {

    @Test
    public void testConstructorAndToString(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "Address:\n1234 Main Street\nApt 2B\nAnycity, ME, 12345\n\n";
        String actual = addy.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeStreetAddress(){
        StreetAddress addy = new StreetAddress();
        addy.changeStreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "Address:\n1234 Main Street\nApt 2B\nAnycity, ME, 12345\n\n";
        String actual = addy.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAddressLine1(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "1234 Main Street";
        String actual = addy.getAddressLine1();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAddressLine2(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "Apt 2B";
        String actual = addy.getAddressLine2();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCity(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "Anycity";
        String actual = addy.getCity();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetState(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "ME";
        String actual = addy.getState();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetZip(){
        StreetAddress addy = new StreetAddress("1234 Main Street", "Apt 2B", "Anycity", "ME", "12345");
        String expected = "12345";
        String actual = addy.getZip();
        Assert.assertEquals(expected, actual);
    }
}
