package tianchi.lewis.indi.im.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-05 22:49
 */
@Component
@Data
public class DSConfig {
    @Value("${ds.username}")
    private String username;

    @Value("${ds.url}")
    private String url;

    @Value("${ds.driver-class-name}")
    private String className;

    @Value("${ds.password}")
    private String password;

    @Value("${ds.ds-name}")
    private String dsName;
}