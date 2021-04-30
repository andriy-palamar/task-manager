package com.kitrum.task.manager.unit.dto;

import com.kitrum.task.manager.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullUnitDto {

    private String id;
    private String name;

    public static FullUnitDto of(Unit unit) {
        return new FullUnitDto(unit.getId(), unit.getName());
    }
}
