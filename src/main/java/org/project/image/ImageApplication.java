package org.project.image;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@MapperScan("org.project.image.mapper")
public class ImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageApplication.class, args);

	}


}
// 扫描全部的mapper @MapperScan("org.project.image.mapper")
// 否则就要在每一个mapper上面加入 @Mapper 比较麻烦
