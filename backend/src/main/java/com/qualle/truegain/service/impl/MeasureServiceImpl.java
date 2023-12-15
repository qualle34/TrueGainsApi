package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.MeasureDto;
import com.qualle.truegain.api.UserMeasureDto;
import com.qualle.truegain.model.entity.Dimension;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.entity.UserDimension;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.repository.DimensionRepository;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.MeasureService;
import com.qualle.truegain.service.mapper.DimensionMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasureServiceImpl implements MeasureService {

    private final DimensionRepository dimensionRepository;
    private final UserRepository userRepository;
    private final DimensionMapper mapper;

    @Override
    public List<MeasureDto> getMeasures() {
        return mapper.toDto(dimensionRepository.findAll());
    }

    @Override
    public MeasureDto getMeasureById(long id, long userId) {
        return mapper.toDto(dimensionRepository.findByIdAndUser(id, userId), List.of("user_measures"));
    }

    @Override
    @Transactional
    public void addMeasure(UserMeasureDto dto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " was not found"));

        Dimension dimension = Dimension.builder().id(dto.getMeasureId()).build();

        UserDimension userDimension = UserDimension.builder()
                .value(dto.getValue())
                .date(dto.getDate())
                .dimension(dimension)
                .user(user)
                .build();

        user.getDimensions().add(userDimension);
        dimension.setUserDimensions(List.of(userDimension));

        userRepository.save(user);
    }
}
