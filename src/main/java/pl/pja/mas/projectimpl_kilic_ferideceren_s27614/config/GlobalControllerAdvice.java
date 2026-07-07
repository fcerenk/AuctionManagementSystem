package pl.pja.mas.projectimpl_kilic_ferideceren_s27614.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.model.*;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.EmployeeRepository;
import pl.pja.mas.projectimpl_kilic_ferideceren_s27614.repository.GalleryMemberRepository;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final GalleryMemberRepository galleryMemberRepository;
    private final EmployeeRepository employeeRepository;

    @ModelAttribute("allMembers")
    public List<GalleryMember> getAllMembers() {
        return galleryMemberRepository.findAll();
    }


    @ModelAttribute("allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


}