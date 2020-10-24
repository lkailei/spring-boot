package com.kaysanshi.springbootwebrestfulcurd.config;

import com.kaysanshi.springbootwebrestfulcurd.filter.MyFilter;
import com.kaysanshi.springbootwebrestfulcurd.listener.MyListener;
import com.kaysanshi.springbootwebrestfulcurd.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 *
 *配置嵌入式的
 */
@Configuration
public class MyServerConfig {
    /**
     * 注册servlet
     * @return
     */
    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /**
     * 注册filter
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    /**
     * 注册listener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }

    /**
     *
     * springboot1.x的相关类如下：
     * org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
     * org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
     * org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer
     * org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
     * springboot2.x的相关类如下：
     * org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
     * org.springframework.boot.web.server.WebServerFactoryCustomizer
     * org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
     * org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     */

    /**
     * springboot1.x配置嵌入式的Servlet容器
     * @return
     */
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //定制嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8083);
//            }
//        };
//    }



    /**
     *spring2.x配置嵌入式的Servert容器
     * 使用spring2.x的配置的形式
     * @return
     *     void setPort(int port);
     *
     *     void setAddress(InetAddress address);
     *
     *     void setErrorPages(Set<? extends ErrorPage> errorPages);
     *
     *     void setSsl(Ssl ssl);
     *
     *     void setSslStoreProvider(SslStoreProvider sslStoreProvider);
     *
     *     void setHttp2(Http2 http2);
     *
     *     void setCompression(Compression compression);
     *
     *     void setServerHeader(String serverHeader);
     */
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer <ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8082);
            }
        };

    }

}
