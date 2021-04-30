package com.kitrum.task.manager.unit;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.unit.dto.FullUnitDto;
import com.kitrum.task.manager.unit.dto.UnitDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDto>> getUnits() {
        List<UnitDto> units = unitService.getUnits();

        return ResponseEntity.ok(units);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullUnitDto> getUnit(@PathVariable String id)
            throws ResourceNotFoundException {

        FullUnitDto unit = unitService.getUnit(id);

        return ResponseEntity.ok(unit);
    }
}
