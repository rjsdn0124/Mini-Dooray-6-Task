package com.nhnacademy.taskapi.entity.project;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    ACTIVE(1),
    INACTIVE(2),
    TERMINATED(3);

    private final Integer projectStatus;

    private ProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public static ProjectStatus fromValue(Integer value) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.getProjectStatus().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

}

