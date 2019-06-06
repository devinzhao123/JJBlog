package wang.dreamland.www.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.dreamland.www.dao.RoleMapper;
import wang.dreamland.www.entity.Role;
import wang.dreamland.www.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Role findById(long id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.selectOne(role);
    }

    @Override
    public int add(Role role) {
        return roleMapper.insert(role);
    }
}
