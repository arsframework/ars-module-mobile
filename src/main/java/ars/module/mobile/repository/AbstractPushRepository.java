package ars.module.mobile.repository;

import ars.module.mobile.model.Push;
import ars.database.hibernate.HibernateSimpleRepository;

/**
 * App消息推送数据持久抽象实现
 *
 * @param <T> 数据模型
 * @author wuyongqiang
 */
public abstract class AbstractPushRepository<T extends Push> extends HibernateSimpleRepository<T>
    implements PushRepository<T> {

}
