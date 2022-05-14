package demo.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 2. 获取cookie
        // 2.1 获取客户端保存的所有cookie，得到一个cookies数组
        Cookie[] cookies = request.getCookies();
        // 2.2 遍历cookies数组，拿到每一个cookie对象
        for (Cookie cookie : cookies) {
            // 2.3 获取cookie中的数据，并做其他的操作（如拿到一个特定键为username的cookie）
            String name = cookie.getName();
            if (name.equals("username")) {
                String value = cookie.getValue();
                value = URLDecoder.decode(value, "UTF-8");   // 额外：中文cookie要进行相应的URL解码
                System.out.println( name + ":" + value );
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
