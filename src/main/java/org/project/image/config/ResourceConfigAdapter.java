package org.project.image.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径 work_project代表项目工程名 需要更改
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Image\\";
        System.out.println("配置文件已经生效");
        //关于图片上传后需要重启服务器才能刷新图片
        //这是一种保护机制，为了防止绝对路径被看出来，目录结构暴露
        //解决方法:将虚拟路径/images/
        //        向绝对路径 (D:\\springboot项目\\src\\main\\resources\\static\\images\\)映射

        registry.addResourceHandler("/image/**").addResourceLocations("file:"+path);



//        String os = System.getProperty("os.name");
//        if (os.toLowerCase().startsWith("win")) {
//            registry.addResourceHandler("/Image/**").
//                    addResourceLocations("file:"+path);
//        }else{//linux和mac系统 可以根据逻辑再做处理
//            registry.addResourceHandler("/Image/**").
//                    addResourceLocations("file:"+path);
//        }
//        super.addResourceHandlers(registry);
    }
}