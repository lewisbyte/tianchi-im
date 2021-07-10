package tianchi.lewis.indi.im.serivce.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-10 18:00
 */
public class BaseDataService {
    protected Snowflake snowflake = IdUtil.createSnowflake(1, 1);
}