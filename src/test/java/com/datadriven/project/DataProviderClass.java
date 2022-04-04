package com.datadriven.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DataProviderClass {

    public ArrayList<String> getData( WebDriver driver,String sheetname) {

        int numberOfEntries = 0 ;
        ArrayList<String> headers = new ArrayList<String>();
        ArrayList<String> fnames = new ArrayList<String>();
        ArrayList<String> lnames = new ArrayList<String>();
        ArrayList<String> emails = new ArrayList<String>();
        ArrayList<String> numbers = new ArrayList<String>();

        WebElement fname =  driver.findElement(By.id("firstName"));
        WebElement lname =  driver.findElement(By.id("lastName"));
        WebElement email =  driver.findElement(By.id("userEmail"));
        WebElement number =  driver.findElement(By.id("userNumber"));


        try {

            // taken the path of the file
            FileInputStream file = new FileInputStream(
                    "C:\\Users\\mahadev.b\\Desktop\\ExcelFilesForDataDriven\\records.xls");

            // take workbook
            HSSFWorkbook workBook = new HSSFWorkbook(file);

            // find number of files present in the work book
            int totalSheets = workBook.getNumberOfSheets();


            for (int i = 0; i < totalSheets; i++) {

                //taking all the sheets present in the excel file
                if (workBook.getSheetName(i).equalsIgnoreCase(sheetname)) {

                    // passing the sheet name which we want to test
                    HSSFSheet sheet = workBook.getSheetAt(i);

                    // after passing the sheet enter into the row and iteratoring the row to find next
                    Iterator<Row> row = sheet.iterator();
                    Row firstRow = row.next();

                    // aftr entering into row entering into the cell one by one
                    Iterator<Cell> cellHeader = firstRow.iterator();
                    while (cellHeader.hasNext()) {

                        Cell ch = cellHeader.next();

                        //grabbing all the headers into one arraylist
                        headers.add(ch.getStringCellValue());
                    }
                    // it return ture if there is next value else false and stops
                    while (row.hasNext()) {

                        // if true it will go to next row
                        Row r = row.next();

                        //starts to iterate the cells
                        Iterator<Cell> ce = r.cellIterator();
                        while (ce.hasNext()) {
                            for (int j = 0; j < headers.size(); j++) {
                                Cell c = ce.next();
                                if (headers.get(j).contains("fname")) {
                                    String fn = c.getStringCellValue();
                                    fnames.add(c.getStringCellValue());
                                    fname.click();
                                    fname.sendKeys(fn);
                                    fname.clear();
                                    System.out.print(++numberOfEntries+" "+fn+"  ");

                                }
                                else if (headers.get(j).contains("lname"))
                                {
                                    String ln = c.getStringCellValue();
                                    lnames.add(ln);
                                    lname.click();
                                    lname.sendKeys(ln);
                                    lname.clear();
                                    System.out.print(ln+"  ");

                                }
//                                else if (headers.get(j).contains("Email Id")) {
//                                    emails.add(c.getStringCellValue());
//                                    String ue = c.getStringCellValue();
//                                    email.click();
//                                    email.sendKeys(ue);
//                                    Thread.sleep(1000);
//                                    email.clear();
//                                    System.out.print(ue+"  ");
//                                }
                                else if (headers.get(j).contains("Phone number")) {
//                                    String un = NumberToTextConverter.toText(c.getNumericCellValue());
//                                double l =    c.getNumericCellValue();


                                    email.click();
                                    email.sendKeys( NumberToTextConverter.toText(c.getNumericCellValue()));
                                    Thread.sleep(1000);
                                    email.clear();
//                                    System.out.print(+"  ");

                                }
                                else
                                {
                                    break;
                                }
                            }
                            System.out.println();
                        }

                    }

                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not present in the given location:");
        } catch (IOException e) {
            System.out.println("Excel file is not found:");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return numbers;

    }
}
