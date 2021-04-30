package com.kitrum.task.manager.user.dto;

import com.kitrum.task.manager.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FullUserDto {

    private String id;
    private String name;
    private Integer rating;

    public static FullUserDto of(User user) {
        return new FullUserDto(user.getId(), user.getName(), null);
    }
}
