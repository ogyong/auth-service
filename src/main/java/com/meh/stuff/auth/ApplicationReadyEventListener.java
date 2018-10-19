package com.meh.stuff.auth;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.meh.stuff.auth.db.entity.Permission;
import com.meh.stuff.auth.db.entity.Role;
import com.meh.stuff.auth.db.entity.User;
import com.meh.stuff.auth.db.exception.EntityNotFoundException;
import com.meh.stuff.auth.service.PermissionService;
import com.meh.stuff.auth.service.RoleService;
import com.meh.stuff.auth.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

	private static final String APPLICATION_PERMISSION = "Application Permission";
	private static final String ADMINISTRATOR_ROLE = "Administrator";

	private UserService userService;
	private PermissionService permissionService;
	private RoleService roleService;

	public ApplicationReadyEventListener(UserService userService, PermissionService permissionService,
			RoleService roleService) {
		this.userService = userService;
		this.permissionService = permissionService;
		this.roleService = roleService;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
		    
            Permission adminPermission = createPermission(APPLICATION_PERMISSION, "Allow user to access all API.");
            permissionService.savePermission(adminPermission);
            
            Role adminRole = createRole(ADMINISTRATOR_ROLE, "Default admin role of the appliction.", Collections.singleton(adminPermission));
            roleService.saveRole(adminRole);
            
            User basicUser = User
            		.builder()
            		.username("administrator")
            		.internalId("572-4")
            		.uuid(UUID.randomUUID())
            		.dateCreated(OffsetDateTime.now())
            		.roles(Collections.singleton(adminRole))
            		.build();
            
            userService.saveUser(basicUser, "test", "test");
        } catch (EntityNotFoundException e) {
            log.error("Unable create base user information.", e);
        }
	}

	private Permission createPermission(String name, String description) {
		return Permission
				.builder()
				.uuid(UUID.randomUUID())
				.permissionName(name)
				.permissionDescription(description)
				.build();
	}

	private Role createRole(String name, String description, Collection<Permission> permissions) {
		return Role
		        .builder()
                .uuid(UUID.randomUUID())
				.roleName(name)
				.roleDescription(description)
				.permissions(new HashSet<>(permissions))
				.build();
	}
}
