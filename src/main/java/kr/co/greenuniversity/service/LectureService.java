package kr.co.greenuniversity.service;

import kr.co.greenuniversity.dto.CollegeDTO;
import kr.co.greenuniversity.dto.LectureDTO;
import kr.co.greenuniversity.entity.College;
import kr.co.greenuniversity.entity.Lecture;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final ModelMapper modelMapper;

    public void registerLecture(LectureDTO lectureDTO){

        Lecture lecture = modelMapper.map(lectureDTO, Lecture.class);
        log.info("college : {}", lecture);

        lectureRepository.save(lecture);

    }
}
