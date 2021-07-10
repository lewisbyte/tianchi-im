package tianchi.lewis.indi.im;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 使用自己的镜像启动自己的应用程序测试后，再咨询，
 * 启动方式，使用镜像id创建ecs，然后讲application.zip拷贝到/tmp目录，
 * 将start.sh拷贝到 ～ 然后使用 start.sh default完成部署和首次运行，
 * 以及start.sh run来运行，完成后再提交，如果没有测试，需要自己测试，
 * 测试完成后，如果还是0分，再at我。另外需要考虑程序的启动时间，可以再
 * start.sh的run中进行sleep比如30秒或者check一下是否启动
 *
 */
@EnableSwagger2
@EnableSwagger2Doc
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("tianchi.lewis.indi.im.dao")
public class ImApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImApplication.class, args);
	}

}
