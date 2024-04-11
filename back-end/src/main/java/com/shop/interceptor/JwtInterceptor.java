package com.shop.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.shop.pojo.Result;
import com.shop.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}",url);

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if(!url.contains("user") && !url.contains("admin")){
            log.info("登录/游客访问, 放行...");
            return true;
        }

        // 3. 获取请求头中的Authorization。
        String authHeader = req.getHeader("Authorization");

        // 4. 判断Authorization头是否存在，如果不存在，返回错误结果（未登录）。
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("请求头Authorization为空或格式不正确,返回未登录的信息");
            Result error = Result.error("NOT LOGIN");
            // 手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        // 5. 提取JWT
        String jwt = authHeader.substring(7); // 移除"Bearer "前缀

        // 6.解析token，如果解析失败，返回错误结果（未登录）。
        try {
            JwtUtil.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT LOGIN");
            //手动转换 对象--json --------> 阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //7.放行。
        log.info("令牌合法, 放行");
        return true;
    }
}
