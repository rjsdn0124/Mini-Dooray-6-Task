package com.nhnacademy.taskapi.converter;

import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProjectStatus projectStatus) {
        return projectStatus != null ? projectStatus.getProjectStatus() : null;
    }

    @Override
    public ProjectStatus convertToEntityAttribute(Integer integer) {
        return integer != null ? ProjectStatus.fromValue(integer) : null;
    }

}
