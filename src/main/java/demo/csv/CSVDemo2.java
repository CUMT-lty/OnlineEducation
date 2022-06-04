package demo.csv;

import com.mooc.pojo.Class;
import com.mooc.pojo.ClassLog;
import com.mooc.service.impl.ClassLogServiceImpl;
import com.mooc.service.impl.ClassServiceImpl;
import com.mooc.service.impl.KnowledgeServiceImpl;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;


/**
 * 使用opencsv进行csv文件操作
 */
public class CSVDemo2 {

  public static KnowledgeServiceImpl knowledgeService = new KnowledgeServiceImpl();
  public static ClassServiceImpl classService = new ClassServiceImpl();
  public static ClassLogServiceImpl classLogService = new ClassLogServiceImpl();

  static String csvPath = "C:\\Users\\86131\\Desktop\\python\\GraduationProject\\classes&kwords.csv";
//  static String charset="utf-8";
  static String[] line;


  /**
   * 从csv读取，写入数据库
   * @throws IOException
   */
  public static void csvToDB() throws IOException {
    CSVReader csvReader = new CSVReader(new FileReader(csvPath));
    while ((line = csvReader.readNext()) != null) {
      // line[0]是课程名字，line[1]是课程的知识点名字，line[2]是课程的等级
      String cName = line[0];
      int kId = knowledgeService.selectKIdByKname(line[1]);
      int level = Integer.parseInt(line[2]);
      // 查看该课程是否已存在
      if ( classService.selectByName(cName) == null) {  // 如果该课程不存在才添加新纪录，否则跳过
        // class表中添加记录
        Class newClass = new Class();
        newClass.setName(cName);
        newClass.setkId(kId);
        newClass.setLevel(level);
        int cId = classService.addClass(newClass);
        // classLog表中添加记录
        ClassLog classLog = new ClassLog();
        classLog.setcId(cId);
        classLog.setcName(cName);
        classLogService.addClassLog(classLog);
      }
    }
  }

  public static void main(String[] args) {
    try {
      csvToDB();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
