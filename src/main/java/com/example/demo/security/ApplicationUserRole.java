package com.example.demo.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    // Management related roles and their permissions
    CUSTOMER(Sets.newHashSet()), // customer does not have any authority on management
    MANAGER(Sets.newHashSet(READ)), // manager has authority for only reading customers, assets, orders etc.
    ADMINISTRATOR(Sets.newHashSet(READ,WRITE)); // administrator has authorities for reading and writing customer, assets, orders etc.


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {

        return permissions;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {

        List<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
