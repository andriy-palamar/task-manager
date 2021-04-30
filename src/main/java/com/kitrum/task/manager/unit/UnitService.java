package com.kitrum.task.manager.unit;

import com.kitrum.task.manager.exception.ResourceNotFoundException;
import com.kitrum.task.manager.unit.dto.FullUnitDto;
import com.kitrum.task.manager.unit.dto.UnitDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<UnitDto> getUnits() {
        List<Unit> units = unitRepository.findAll();

        return units.stream()
                .map(UnitDto::of)
                .collect(Collectors.toList());
    }

    public FullUnitDto getUnit(String id)
            throws ResourceNotFoundException {

        return unitRepository.findById(id)
                .map(FullUnitDto::of)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", id));
    }
}
