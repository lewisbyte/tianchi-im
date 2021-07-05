package tianchi.lewis.indi.im.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: tianchi-im
 * @description: t_user
 * @author: lewis
 * @create: 2021-07-03 01:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "t_user")
public class TUser extends Base {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}