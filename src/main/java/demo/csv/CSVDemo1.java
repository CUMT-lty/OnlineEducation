package demo.csv;

import java.io.*;

public class CSVDemo1 {

  public static void main(String[] args) {
    String csvPath = "C:\\Users\\86131\\Desktop\\python\\GraduationProject\\classes&kwords.csv";
    String charset="utf-8";
    try {
//      File csvFile = new File(csvPath);
      FileInputStream fileInputStream = new FileInputStream(csvPath);
      BufferedReader csvReader = new BufferedReader(new InputStreamReader(fileInputStream, charset));
      String lineDta = "";
      while ((lineDta = csvReader.readLine()) != null ){
        System.out.println(lineDta);
      }
      csvReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("没有找到指定文件！");
    } catch (IOException e){
      System.out.println("文件读写出错！");
    }
  }

}
