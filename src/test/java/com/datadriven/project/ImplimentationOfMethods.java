package com.datadriven.project;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ImplimentationOfMethods   implements InterfaceMethods
{

    @Override
    public String fNameMethod(String fname, WebElement element)
    {
        element.click();
        element.sendKeys(fname);
        element.clear();
        return fname;
    }
}
