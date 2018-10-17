package com.meh.stuff.auth.api.dto;

import com.meh.stuff.auth.db.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	private int id;
	
	public static UserDto of(User user) {
		return UserDto
				.builder()
				.id(user.getId())
				.build();
	}
}
