package cc.learn.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cc.learn.util.HttpClientUtil;
import cc.learn.util.ResponseResultVo;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月23日 上午8:39:10
 */
@Component
public class UnicomTask {

    private final static Log logger = LogFactory.getLog(UnicomTask.class);

    private final String url = "https://act.10010.com/SigninApp/signin";
    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NDM5NzQzMTAsInRva2VuIjp7ImxvZ2luVXNlciI6IjEzMDA4ODEyNzE3IiwicmFuZG9tU3RyIjoieWhjM09uY0cxNTQzMzY5NTEwMDE2In0sImlhdCI6MTU0MzM2OTUxMH0.22CDYYVfI5oF9mWEGPdLex1MpciayclJb45sR4FwpPb0eqTh6ptWLQiUFJ-TRCOec-UNnFAFSQwT4y2dvotE6A";
    private final String signFlag = "eyJkYXRhIjoiMTMyYzJlNGFmOTFiOWU0ZTRmMmMyMDQwOWVkNWU5NDJjOWM1M2RmZmU3OGIwMTUyYTcyMzU5ZTFmNjljMGIwYjAxNTIxNTU4OTg5MWZlOGYxMDZmYWYyOTBlMGZkMWZlOWJlNDdkYzE0ZDFkZDU4MTEzM2ZmMTc0MTcyYzAzNGI0YWVmYTM2YWZjOTNiOTliNmEzZTc3YmIxMTg3N2I0ZiIsInZlcnNpb24iOiIwMCJ9";
    //腾讯视频vip
    private final String productId = "ff80808166cf1a8f01672b89b01b0a8f";
    //通过token获取登陆信息，产生cookie
    private final String loginUrl = url + "/querySigninActivity.htm?token=" + token + "&signFlag=" + signFlag;

    @Scheduled(cron = "0 0 7 * * ?")
    public void singIn() {
        /* String doResult = "";
        //签到
        String postUrl = url + "/daySign.do";
        try {
            ResponseResultVo doGet = HttpClientUtil.doGet(loginUrl, null);
            doResult = HttpClientUtil.doPost(postUrl, null, doGet.getCookieStore());
            logger.info(doResult);
            boolean success = doResult.contains("continuCount") || doResult.contains("签到成功");
            while (!success) {
                Thread.sleep(5000);
                singIn();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int mostTry = 5;
            int hasTry = 0;
            @Override
            public void run() {
                String doResult = "";
                //签到
                String postUrl = url + "/daySign.do";
                ResponseResultVo doGet = HttpClientUtil.doGet(loginUrl, null);
                doResult = HttpClientUtil.doPost(postUrl, null, doGet.getCookieStore());
                logger.info(doResult);
                boolean success = doResult.contains("continuCount") || doResult.contains("签到成功") || hasTry >= mostTry;
                if (success) {
                    timer.cancel();
                } else {
                    hasTry++;
                }
            }
        }, 5000, 10000);

    }

    public void exchageGift() {
        //兑换礼品
        String postUrl = url + "/exchange.do?productId=" + productId + "&position=0&type=1";
        ResponseResultVo doGet = HttpClientUtil.doGet(loginUrl, null);
        String doPost = HttpClientUtil.doPost(postUrl, null, doGet.getCookieStore());
        logger.info(doPost);
    }

    public static void main(String[] args) throws ParseException {
        UnicomTask unicomTask = new UnicomTask();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2018-11-26 14:59:58");
        Timer timer = new Timer();
        System.err.println(dateFormat.format(new Date()));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                unicomTask.exchageGift();
                System.err.println(dateFormat.format(new Date()));
            }
        }, date, 1000);
    }

}
