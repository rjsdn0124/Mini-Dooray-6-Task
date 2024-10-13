package com.nhnacademy.taskapi.service.milestone.impl;

import com.nhnacademy.taskapi.entity.milestone.MileStone;
import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.milestone.dto.MileStoneDto;
import com.nhnacademy.taskapi.entity.milestone.request.MileStoneRequest;
import com.nhnacademy.taskapi.error.milestone.MileStoneAlreadyExistsException;
import com.nhnacademy.taskapi.error.milestone.MileStoneNotFoundException;
import com.nhnacademy.taskapi.error.project.ProjectNotFoundException;
import com.nhnacademy.taskapi.error.projectmember.ProjectMemberUserNotFoundException;
import com.nhnacademy.taskapi.repository.milestone.MileStoneRepository;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.service.milestone.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MileStoneServiceImpl implements MileStoneService {

    private final ProjectMemberRepository projectMemberRepository;
    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<MileStoneDto> getAllMileStones() {
        List<MileStone> mileStoneList = mileStoneRepository.findAll();

        List<MileStoneDto> mileStoneDtoList = new ArrayList<>();
        for (MileStone mileStone : mileStoneList) {

            mileStoneDtoList.add(new MileStoneDto(mileStone.getTitle(),
                                                    mileStone.getInitDate(),
                                                    mileStone.getDueDate()));
        }
        return mileStoneDtoList;
    }

    @Override
    public MileStoneDto create(String userId, String title, LocalDate initDate, LocalDate dueDate, Long projectId) {
        if (Objects.isNull(title) || Objects.isNull(projectId) || title.isEmpty() || projectId < 0) {
            throw new IllegalArgumentException("마일스톤에 필요한 값이 올바르지 않습니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException(userId);
        }
        if (mileStoneRepository.existsByTitle(title)) {
            throw new MileStoneAlreadyExistsException();
        }


        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        mileStoneRepository.save(new MileStone(title, initDate, dueDate, project));

        return new MileStoneDto(title, initDate, dueDate);
    }

    @Override
    public void delete(Long mileStoneId, String userId) {
        if (Objects.isNull(mileStoneId) || mileStoneId < 0) {
            throw new IllegalArgumentException("마일스톤 값이 올바르지 않습니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException(userId);
        }
        if (!mileStoneRepository.existsById(mileStoneId)) {
            throw new MileStoneNotFoundException();
        }


        mileStoneRepository.delete(mileStoneRepository.findById(mileStoneId)
                                                        .orElseThrow(MileStoneNotFoundException::new));
    }

    @Override
    public MileStoneDto update(Long mileStoneId, MileStoneRequest request, String userId) {
        if (Objects.isNull(mileStoneId) || mileStoneId < 0) {
            throw new IllegalArgumentException("마일스톤 값이 올바르지 않습니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException(userId);
        }
        if (!mileStoneRepository.existsById(mileStoneId)) {
            throw new MileStoneNotFoundException();
        }


        MileStone mileStone = mileStoneRepository.findById(mileStoneId).orElseThrow(MileStoneNotFoundException::new);
        mileStone.setTitle(request.getTitle());
        mileStone.setInitDate(request.getInitDate());
        mileStone.setDueDate(request.getDueDate());

        mileStoneRepository.save(mileStone);

        return getMileStoneDto(mileStone);
    }

    private static MileStoneDto getMileStoneDto(MileStone mileStone) {
        MileStoneDto mileStoneDto;

        mileStoneDto = new MileStoneDto(mileStone.getTitle(),
                                        mileStone.getInitDate(),
                                        mileStone.getDueDate());
        return mileStoneDto;
    }

    @Override
    public MileStoneDto getByMileStoneId(Long mileStoneId) {
        if (Objects.isNull(mileStoneId) || mileStoneId < 0) {
            throw new IllegalArgumentException("마일스톤 값이 올바르지 않습니다.");
        }
        if (!mileStoneRepository.existsById(mileStoneId)) {
            throw new MileStoneNotFoundException();
        }

        MileStone mileStone = mileStoneRepository.findById(mileStoneId).orElseThrow(MileStoneNotFoundException::new);

        return getMileStoneDto(mileStone);
    }

    @Override
    public List<MileStoneDto> getAllMieStonesByProjectId(Long projectId) {
        List<MileStone> mileStoneList = mileStoneRepository.findAllByProjectId(projectId);

        List<MileStoneDto> mileStoneDtoList = new ArrayList<>();
        for (MileStone mileStone : mileStoneList) {

            mileStoneDtoList.add(new MileStoneDto(mileStone.getTitle(),
                                                    mileStone.getInitDate(),
                                                    mileStone.getDueDate()));

        }

        return mileStoneDtoList;
    }
}
