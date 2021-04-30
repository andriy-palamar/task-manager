package com.kitrum.task.manager.unit.dto;

import com.kitrum.task.manager.unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitDto {

    private String id;
    private String name;

    public static UnitDto of(Unit unit) {
        return new UnitDto(unit.getId(), unit.getName());
    }
}
