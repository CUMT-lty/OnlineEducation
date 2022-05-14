package demo.json;

import com.alibaba.fastjson.JSON;
import com.mooc.pojo.Stu;

public class JsonDemo1 {

    public static void main(String[] args) {
        // java对象转json
        Stu user = new Stu();
        user.setId(1);
        user.setUsername("cumtlty");
        user.setPassword("lty123");
        user.setName("lty");
        user.setSchool("cumt");
        user.setProfession("cs");

        String strJson = JSON.toJSONString(user);
//        System.out.println("obj转json的结果是："+strJson);

        // json转java对象1
        Stu jsonUser = JSON.parseObject(strJson, Stu.class);
//        System.out.println("json转obj的结果是："+jsonUser);

        // json转java对象2
        String str = "{" +
                "\"id\":1," +
                "\"name\":\"lty\"," +
                "\"password\":\"lty123\"," +
                "\"profession\":\"cs\"," +
                "\"school\":\"cumt\"," +
                "\"username\":\"cumtlty\"" +
                "}";
        Stu user1 = JSON.parseObject(str, Stu.class);
        System.out.println(user1);


        /* json转为java对象需要键名和实体类属性名一致 */

    }

}
