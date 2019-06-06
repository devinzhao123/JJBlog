package wang.dreamland.www.service;
import wang.dreamland.www.entity.Role;

import javax.validation.constraints.Size;
import java.util.List;

public interface RoleService {
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role findById(long id);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int add(Role role);

    /**
     * 根据用户id查询所有角色
     */
    List<Role> findByUid(Long uid);
}
