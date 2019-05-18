/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Novak
 */
public class Ai {

    double x4[] = new double[90001];
    String x4s[] = new String[90001];
    double x7[] = new double[90001];
    double hasil[] = new double[90001];
    String x7s[] = new String[90001];
    int total = 0;
    double yee[] = new double[90001];
    /**
     * @param args the command line arguments
     */
    
    public void convert() {
        for (int i = 1; i < total; i++) {
            x4[i] = (100/2)*(x4[i]-0)+100;
            x7[i] = (100/2)*(x7[i]-2)+100;
        }
    }
    
    public void comparison() {
        double bener=0;
        for (int i = 1; i < total; i++) {
            if (hasil[i]==yee[i]) {
                bener++;
            }
        }
        System.out.println(bener);
        double akurasi = (bener/90000)*100;
        System.out.println("Akurasi : "+akurasi+" %");
    }
    
    public void inferensi(){
        for (int i = 1; i < total; i++) {            
            if (x4s[i].equals("LOWLOW")) {
                yee[i]=1;                
            } else if (x4s[i].equals("LOW") && x7s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("LOW")) {
                yee[i]=1;                
            } else if ((x4s[i].equals("MID") && x7s[i].equals("MEGAHIGH")) || (x4s[i].equals("MID") && x7s[i].equals("HIGHHIGH"))) {
                yee[i]=0;
            } else if (x4s[i].equals("MID")) {
                yee[i]=1;
            } else if (x4s[i].equals("HIGH") && x7s[i].equals("HIGH")) {
                yee[i]=1;
            } else if (x4s[i].equals("HIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("HIGHHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } 
        }        
    }
    
    public void inferensi2(){
        for (int i = 1; i < total; i++) {            
            if (x4s[i].equals("LOWLOW")) {
                yee[i]=1;                
            } else if (x4s[i].equals("LOW") && x7s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("LOW")) {
                yee[i]=1;                
            } else if ((x4s[i].equals("MID") && x7s[i].equals("HIGHHIGH")) || (x4s[i].equals("MID") && x7s[i].equals("HIGHHIGH"))) {
                yee[i]=0;
            } else if (x4s[i].equals("MID") && x7s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("MID")) {
                yee[i]=1;
            } else if (x4s[i].equals("HIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("HIGHHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } 
        }        
    }    
    
    public void inferensi3(){
        for (int i = 1; i < total; i++) {            
            if (x4s[i].equals("LOWLOW")) {
                yee[i]=1;                
            } else if (x4s[i].equals("LOW") && x7s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("LOW")) {
                yee[i]=1;                
            } else if (x4s[i].equals("MID") && x7s[i].equals("LOWLOW")) {
                yee[i]=1;
            } else if ((x4s[i].equals("MID") && x7s[i].equals("MEGAHIGH")) || (x4s[i].equals("MID") && x7s[i].equals("HIGHHIGH"))) {
                yee[i]=0;
            } else if (x4s[i].equals("MID")) {
                yee[i]=1;            
            } else if (x4s[i].equals("HIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("HIGHHIGH")) {
                yee[i]=0;
            } else if (x4s[i].equals("MEGAHIGH")) {
                yee[i]=0;
            } 
        }        
    }    
    
    public void fuzzy() {
        double lowlow = 10;
        double low = 31;
        double mid = 52;
        double high = 73;
        double highhigh = 79;
        double megahigh = 100;
        for (int i = 1; i < total; i++) {
            if (x4[i]<=lowlow) {
                x4s[i] = "LOWLOW";
            } else if (x4[i]<=low) {
                x4s[i] = "LOW";
            } else if (x4[i]<=mid) {
                x4s[i] = "MID";
            } else if (x4[i]<=high) {
                x4s[i] = "HIGH";
            } else if (x4[i]<=highhigh) {
                x4s[i] = "HIGHHIGH";
            } else {
                x4s[i] = "MEGAHIGH";
            }
            if (x7[i]<=lowlow) {
                x7s[i] = "LOWLOW";
            } else if (x7[i]<=low) {
                x7s[i] = "LOW";
            } else if (x7[i]<=mid) {
                x7s[i] = "MID";
            } else if (x7[i]<=high) {
                x7s[i] = "HIGH";
            } else if (x7[i]<=highhigh) {
                x7s[i] = "HIGHHIGH";
            } else {
                x7s[i] = "MEGAHIGH";
            }
        }
    }
    
    public void isi() throws FileNotFoundException, IOException {
        File f = new File("Train.xlsx");
        FileInputStream file = new FileInputStream(f);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator forlula = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 4) {
                    switch(forlula.evaluateInCell(cell).getCellType()){
                        case (Cell.CELL_TYPE_NUMERIC):
                            x4[total] = cell.getNumericCellValue();
                            break;
                    }
                } else if (cell.getColumnIndex() == 7) {
                    switch(forlula.evaluateInCell(cell).getCellType()){
                        case (Cell.CELL_TYPE_NUMERIC):
                            x7[total] = cell.getNumericCellValue();
                            break;
                    }
                } else if (cell.getColumnIndex() == 11) {
                    switch(forlula.evaluateInCell(cell).getCellType()){
                        case (Cell.CELL_TYPE_NUMERIC):
                            hasil[total] = cell.getNumericCellValue();
                            break;
                    }
                }
            }
            total++;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic her
        Ai a = new Ai();
        a.isi();
        a.convert();
        a.fuzzy();
        a.inferensi3();
        for (int i = 1; i < a.total; i++) {
            System.out.print(i+" ");
            System.out.println(a.x4s[i]+"\t\t"+a.x7s[i]+"\t\t\t"+a.hasil[i]+"\t"+a.yee[i]);
        }
        a.comparison();
    }

}
