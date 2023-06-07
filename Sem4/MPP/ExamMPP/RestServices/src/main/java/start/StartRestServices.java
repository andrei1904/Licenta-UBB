package start;

import app.persistence.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("app")
@SpringBootApplication
public class StartRestServices {
    public static void main(String[] args) {
        SpringApplication.run(StartRestServices.class, args);
    }

//    @Bean(name = "hibernateUtil")
//    public HibernateUtil getHibernateUtil() {
//        return new HibernateUtil();
//    }
}
