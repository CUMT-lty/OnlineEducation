package demo.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/resp2")
public class ResponseDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 1.读取文件 */
        FileInputStream fis = new FileInputStream("C:\\Users\\86131\\Desktop\\Onlineeducation\\src\\main\\webapp\\hero.png");
        /* 2. 获取response字节输出流 */
        ServletOutputStream os = response.getOutputStream();
        /* 3.完成两个流的copy */
        /*byte[] buff = new byte[1024];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        fis.close();*/

        /* 使用IO工具类来完成流的copy */
        IOUtils.copy(fis, os);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
