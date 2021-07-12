package tianchi.lewis.indi.im.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: tianchi-im
 * @description: 实体基类
 * @author: lewis
 * @create: 2021-07-03 11:57
 */
@Data
public class Base {
    @TableId(type = IdType.ASSIGN_ID)
    Long id;
}