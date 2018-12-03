package cc.learn.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午9:27:47
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /** 
     * Description  
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()   
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /** 
     * Description  
     * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()   
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[] { WebConfig.class };
    }

    /** 
     * Description  
     * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()   
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
