package ars.module.mobile.repository;

import ars.module.mobile.model.Push;
import ars.database.repository.Repository;

/**
 * App消息推送数据持久接口
 * 
 * @author yongqiangwu
 * 
 * @param <T>
 *            数据模型
 */
public interface PushRepository<T extends Push> extends Repository<T> {

}
