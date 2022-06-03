package demo.csv;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVDemo2 {

  public static void main(String[] args) {
    String csvPath = "C:\\Users\\86131\\Desktop\\python\\GraduationProject\\classes&kwords.csv";
    String charset="utf-8";
    String[] line;
    try {
      CSVReader csvReader = new CSVReader(new FileReader(csvPath));
      while ((line = csvReader.readNext()) != null) {
        System.out.println(line[0]+" "+line[1]+" "+line[2]);
      }
    } catch (FileNotFoundException e) {
      System.out.println("没有找到指定文件！");
    } catch (IOException e) {
      System.out.println("文件读写出错了！");
    }
  }

}
