package kr.co.greenuniversity.repository;

import kr.co.greenuniversity.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    // 교수 ID로 검색 (기본 제공됨)
    Optional<Professor> findById(int id);

    // 특정 학과 이름 기반으로 교수 조회
    //List<Professor> findByDepartments_Department_name(String departmentName);

    // 특정 단과대 + 학과에 속한 교수 조회
    //List<Professor> findByDepartments_College_CollegeNameAndDepartments_Department_name(String collegeName, String departmentName);
}
