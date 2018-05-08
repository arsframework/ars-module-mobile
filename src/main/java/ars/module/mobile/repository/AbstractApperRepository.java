package ars.module.mobile.repository;

import ars.module.mobile.model.Apper;
import ars.database.hibernate.HibernateSimpleRepository;

/**
 * App用户数据持久抽象实现
 *
 * @param <T> 数据模型
 * @author wuyongqiang
 */
public abstract class AbstractApperRepository<T extends Apper> extends HibernateSimpleRepository<T>
    implements ApperRepository<T> {

}
