package demo.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 发送cookie
        // 1.1 创建cookie对象
        /*Cookie cookie = new Cookie("username", "李天宇");*/
        /* 上面一行代码会报错，cookie不支持中文，如果想要设置中文cookie，要进行转码，使用URL编码 */

        /* 额外：对中文cookie进行转码 */
//        String value = "李天宇";
//        value = URLEncoder.encode(value, "UTF-8");  // 将使用URL编码后的value重新赋回去
//        Cookie cookie = new Cookie("username", value);  // 把URL编码后的value放进cookie中

//        Cookie cookie = new Cookie("userId", String.valueOf(1));

        // 1.2 发送cookie
//        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("stuId".equals(cookie.getName())) {
                System.out.println(cookie.getValue());
                break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
