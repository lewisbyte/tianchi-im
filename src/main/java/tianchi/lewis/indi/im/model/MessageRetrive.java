package tianchi.lewis.indi.im.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 10:15
 */
@Getter
@Setter
public class MessageRetrive {
    private long id;
    private String text;
    private String timestamp;
}