package cc.learn.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @Description:
 * @author: C
 * @date: 2018年11月20日 下午4:12:39
 */
public class Test {

    public void postTest() {
        String host = "https://mall.phicomm.com";
        String buyUrl = host + "index.php/cart-fastbuy-12-1.html";
        String url = "https://www.baidu.com";
        String loginUrl = "https://mall.phicomm.com/passport-login.html";
        Document document = null;
        //ResponseResultVo doGet = HttpClientUtil.doGet(loginUrl, null);
        try {
            document = Jsoup.connect(loginUrl).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //System.out.println(doGet.getResult());
        System.out.println(document.html());
    }

    public static void main(String[] args) throws ParseException {
        //        Test test = new Test();
        //        test.postTest();

        String url = "https://act.10010.com/SigninApp/signin/querySigninActivity.htm?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NDM5NzQzMTAsInRva2VuIjp7ImxvZ2luVXNlciI6IjEzMDA4ODEyNzE3IiwicmFuZG9tU3RyIjoieWhjM09uY0cxNTQzMzY5NTEwMDE2In0sImlhdCI6MTU0MzM2OTUxMH0.22CDYYVfI5oF9mWEGPdLex1MpciayclJb45sR4FwpPb0eqTh6ptWLQiUFJ-TRCOec-UNnFAFSQwT4y2dvotE6A&signFlag=eyJkYXRhIjoiMTMyYzJlNGFmOTFiOWU0ZTRmMmMyMDQwOWVkNWU5NDJjOWM1M2RmZmU3OGIwMTUyYTcyMzU5ZTFmNjljMGIwYjAxNTIxNTU4OTg5MWZlOGYxMDZmYWYyOTBlMGZkMWZlOWJlNDdkYzE0ZDFkZDU4MTEzM2ZmMTc0MTcyYzAzNGI0YWVmYTM2YWZjOTNiOTliNmEzZTc3YmIxMTg3N2I0ZiIsInZlcnNpb24iOiIwMCJ9";
        ResponseResultVo doGet = HttpClientUtil.doGet(url, null);
        CookieStore cookieStore = doGet.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("name:" + cookie.getName() + ", value:" + cookie.getValue());
        }
    }

    public void doMyTimer() {
        Timer timer = new Timer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse("2018-11-22 14:59:58");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        timer.schedule(new Test().new MyTask(), date, 10000);
    }

    class MyTask extends TimerTask {

        @Override
        public void run() {
            postTest();
        }

    }
}