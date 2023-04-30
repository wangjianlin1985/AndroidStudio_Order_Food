package cn.itfxq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("cn.itfxq")
public class FoodsApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FoodsApp.class);

        springApplication.run(FoodsApp.class,args);
    }
}
