package producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ZkprApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ZkprApplication.class, args);
	}
}
