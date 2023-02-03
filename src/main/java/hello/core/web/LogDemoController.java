package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody       //HttpServletRequest를 통해서 요청 URL을 받음
    public String logDemo(HttpServletRequest request) throws InterruptedException{
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        // myLogger는 request scope이기 때문에 HTTP 요청마다 각각 구분되므로 값이 섞이지않음

        myLogger.log("contrller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
