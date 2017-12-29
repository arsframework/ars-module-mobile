package ars.module.mobile.service;

import ars.invoke.local.Api;
import ars.module.mobile.model.Apper;
import ars.database.service.Service;
import ars.database.service.SearchService;
import ars.database.service.DeleteService;

/**
 * App用户业务操作接口
 * 
 * @author yongqiangwu
 * 
 * @param <T>
 *            数据模型
 */
@Api("mobile/apper")
public interface ApperService<T extends Apper> extends Service<T>, SearchService<T>, DeleteService<T> {

}