package ars.module.mobile.service;

import ars.module.mobile.model.Apper;
import ars.database.service.StandardGeneralService;

/**
 * App用户业务操作抽象实现
 *
 * @param <T> 数据模型
 * @author wuyongqiang
 */
public abstract class AbstractApperService<T extends Apper> extends StandardGeneralService<T>
    implements ApperService<T> {

}
