package kr.co.greenuniversity.controller.admin;

import kr.co.greenuniversity.dto.DepartmentDTO;
import kr.co.greenuniversity.entity.Professor;
import kr.co.greenuniversity.repository.CollegeRepository;
import kr.co.greenuniversity.service.admin.DepartmentService;
import kr.co.greenuniversity.service.admin.ProfessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;
    private final CollegeRepository collegeRepository;
    private final ProfessorService professorService;

    @PostMapping("/Management/registerDepart")
    public String registerDepartment(@ModelAttribute DepartmentDTO departmentDTO) {

        departmentService.registerDepartment(departmentDTO);
        return "redirect:/Management/ManageDepartRegist";
    }

    @GetMapping("/Management/ManageDepartRegist")
    public String showDepartmentForm(Model model, Pageable pageable) {

        Page<Professor> professors = professorService.findAllProfessors(pageable);
        model.addAttribute("professors", professors);

        int nextNo = departmentService.getNextDepartmentNo();

        if (nextNo < 10) nextNo = 10;
        String formattedNo = String.format("%02d", nextNo);

        model.addAttribute("nextNo", formattedNo);
        model.addAttribute("colleges", collegeRepository.findAll());

        return "/Management/ManageDepartRegist";
    }

    @GetMapping("/department/engineering")
    public String engineering() {
        return "/department/engineering";
    }
    @GetMapping("/department/graduateSchool")
    public String graduateSchool() {
        return "/department/graduateSchool";
    }
    @GetMapping("/department/humanities")
    public String humanities() {
        return "/department/humanities";
    }
    @GetMapping("/department/naturalscience")
    public String naturalscience() {
        return "/department/naturalscience";
    }
    @GetMapping("/department/teacher")
    public String teacher() {
        return "/department/teacher";
    }

}
