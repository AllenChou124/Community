package life.majiang.community.community.controller;


import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author 12418
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null) {
                    //写到session里面去 让前端去展示 “我” 而不是登录
                    //解决的问题是 如果不设置一个token到cookie里去，每次重启服务器后
                    //都要重新登录，因为记不住登录过了，所以在某个用户登录过后，
                    // add一个cookie，写一个token到cookie里，下次再登录的时候，直接在user表里
                    //根据这个token来找 看有没有这个用户 有就代表直接登录了。
                    request.getSession().setAttribute("user",user);
                }
            }
        }

        return "index";
    }
}
