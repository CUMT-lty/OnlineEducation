package demo.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/sessionDemo2")
public class SessionDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在sessionDemo1中创建了session对象，并向session对象中存储了数据
        // 现在要实现同一session的资源贡献，也就是要从sessionDemo2中获取session数据
        // 1. 获取session对象
        /* 额外：在一次会话的多次请求期间获取的session是同一个 */
        HttpSession session = request.getSession();
        // 2. 获取数据
        Object username = session.getAttribute("username");
        System.out.println(username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
