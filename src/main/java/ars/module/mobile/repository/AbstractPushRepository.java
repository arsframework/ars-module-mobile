package ars.module.mobile.repository;

import ars.module.mobile.model.Push;
import ars.module.mobile.repository.PushRepository;
import ars.database.hibernate.HibernateSimpleRepository;

/**
 * App消息推送数据持久抽象实现
 * 
 * @author yongqiangwu
 * 
 * @param <T>
 *            数据模型
 */
public abstract class AbstractPushRepository<T extends Push> extends HibernateSimpleRepository<T>
		implements PushRepository<T> {

}
