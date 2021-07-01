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
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}