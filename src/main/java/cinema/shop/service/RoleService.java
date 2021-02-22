package cinema.shop.service;

import cinema.shop.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
