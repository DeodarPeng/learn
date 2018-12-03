package cc.learn.util;

import org.apache.http.client.CookieStore;

import lombok.Data;
import lombok.Getter;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月22日 下午6:00:12
 */
@Data
public class ResponseResultVo {

    private CookieStore cookieStore;
    private String result;
}
