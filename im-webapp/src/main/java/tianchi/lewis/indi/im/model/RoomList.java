package tianchi.lewis.indi.im.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tianchi.lewis.indi.im.entity.TRoom;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 10:16
 */
@Getter
@Setter
@AllArgsConstructor
public class RoomList {
    private String name;
    private String id;

    public RoomList(TRoom room) {
        this.setId(room.getId().toString());
        this.setName(room.getName());
    }
}