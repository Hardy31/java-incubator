package boost.brain.course.projects.model;

import boost.brain.course.common.projects.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapper {

    public ProjectDto toProjectDto(Project project) {
        if (project == null) return null;
        ProjectDto projectDTO = new ProjectDto();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setProjectUrl(project.getProjectUrl());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setAuthor(project.getAuthor());
        return projectDTO;
    }

    public Project toProject(ProjectDto projectDTO) {
        if (projectDTO == null) return null;
        Project project = new Project();
        project.setDescription(projectDTO.getDescription());
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectUrl(projectDTO.getProjectUrl());
        project.setStatus(projectDTO.getStatus());
        project.setAuthor(projectDTO.getAuthor());
        return project;
    }
    public List<ProjectDto> toProjectDtos(List<Project> projects) {
        List<ProjectDto> projectDtos = new ArrayList<>();
        if (projects == null || projects.isEmpty()) return projectDtos;
        for (Project project : projects){
            projectDtos.add(this.toProjectDto(project));
        }
        return projectDtos;
    }
    public Page<ProjectDto> toProjectDtoPage(Page<Project> projects) {
        if (projects == null) return null;
        Page<ProjectDto> projectDtoPage = projects.map(this::toProjectDto);
        return projectDtoPage;
    }
}
