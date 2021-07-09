package tianchi.lewis.indi.im.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tianchi.lewis.indi.im.entity.TMessage;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 10:15
 */
@Getter
@Setter
@AllArgsConstructor
public class Message {
    private String id;
    private String text;
    private Long timestamp;

    public Message(TMessage tMessage) {
        this.setId(tMessage.getId());
        this.setText(tMessage.getText());
        this.setTimestamp(tMessage.getTimestamp());
    }
}