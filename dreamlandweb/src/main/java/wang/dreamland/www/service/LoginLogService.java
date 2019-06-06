package wang.dreamland.www.service;

import wang.dreamland.www.entity.LoginLog;

import java.util.List;

public interface LoginLogService {
    /**
     * 添加登录日志
     * @param LoginLog
     * @return
     */
    int add(LoginLog LoginLog);

    /**
     * 查询所有日志
     * @return
     */
    List<LoginLog> findAll();

    /**
     * 根据用户id查询日志集合
     * @param uid
     * @return
     */
    List<LoginLog> findByUid(Long uid);
}
