package com.demo.course.courseunitprioritizer.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourseDependencyHolderFacade {

    private Path coursesFilePath;
    private Path prerequisitesFilePath;

    public CourseDependencyHolderFacade(Path coursesFilePath, Path prerequisitesFilePath) {
        this.coursesFilePath = coursesFilePath;
        this.prerequisitesFilePath = prerequisitesFilePath;
    }

    public Map<Course, List<Course>> generate() throws FileProcessingServiceException {

        FileProcessingService<Map<Integer,Course>> coursesFile = new FileProcessingService<Map<Integer,Course>>(coursesFilePath);
        
        CourseDetailsOrganizerFunction courceDetailsOrganizerFunction = new CourseDetailsOrganizerFunction();
        Map<Integer,Course> courseDetailsHolder = coursesFile.processFile(courceDetailsOrganizerFunction::execute);

        FileProcessingService<Map<Course, List<Course>>> prerequisitesFile = new FileProcessingService<Map<Course, List<Course>>>(prerequisitesFilePath);
        
        CourcePrerequisitesOrganizerFunction courcePrerequisitesOrganizerFunction = new CourcePrerequisitesOrganizerFunction(courseDetailsHolder);
        return prerequisitesFile.processFile(courcePrerequisitesOrganizerFunction::execute);
    }
}
