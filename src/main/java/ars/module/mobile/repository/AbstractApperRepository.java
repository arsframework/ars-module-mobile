package ars.module.mobile.repository;

import ars.module.mobile.model.Apper;
import ars.module.mobile.repository.ApperRepository;
import ars.database.hibernate.HibernateSimpleRepository;

/**
 * App用户数据持久抽象实现
 * 
 * @author yongqiangwu
 * 
 * @param <T>
 *            数据模型
 */
public abstract class AbstractApperRepository<T extends Apper> extends HibernateSimpleRepository<T>
		implements ApperRepository<T> {

}
