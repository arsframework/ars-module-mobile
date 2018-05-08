package ars.module.mobile.repository;

import ars.module.mobile.model.Apper;
import ars.database.repository.Repository;

/**
 * App用户数据持久接口
 *
 * @param <T> 数据模型
 * @author wuyongqiang
 */
public interface ApperRepository<T extends Apper> extends Repository<T> {

}
