package com.kitrum.task.manager.user.dto;

import com.kitrum.task.manager.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String id;
    private String name;

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
