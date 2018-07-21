package com.demo.course.courseunitprioritizer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.demo.course.courseunitprioritizer.model.Course;
import com.demo.course.courseunitprioritizer.service.CourseDependencyHolderFacade;
import com.demo.course.courseunitprioritizer.service.CourseDependencyOrganizingService;
import com.demo.course.courseunitprioritizer.service.FileProcessingServiceException;

public class CourseOrderOrganizer {

    public static void main(String[] args) throws FileProcessingServiceException, URISyntaxException {

        Path coursesFilePath = Paths.get(ClassLoader.getSystemResource("courses.csv").toURI());
        Path prerequisitesFilePath = Paths.get(ClassLoader.getSystemResource("prerequisites.csv").toURI());

        Map<Course, List<Course>> courseDependentMap = new CourseDependencyHolderFacade(coursesFilePath, prerequisitesFilePath).generate();

        printCourseDependencyStructure(courseDependentMap);

        List<Course> organisedDependencyStructure = new CourseDependencyOrganizingService().execute(courseDependentMap);

        printCourseFollowingOrder(organisedDependencyStructure);
    }


    private static void printCourseDependencyStructure(Map<Course, List<Course>> courseDependentMap) {
        System.out.println("\t\t\t\t>>>> Courses And Dependencies <<<<");

        courseDependentMap.forEach((mainCourse, dependentCourses) -> {
            System.out.println("\nCourse: " + mainCourse);
            System.out.println("Depends On: ");
            dependentCourses.forEach(c -> System.out.println("\t" + c));
        });
    }

    private static void printCourseFollowingOrder(List<Course> organisedDependencyStructure) {
        System.out.println("\n\t\t\t\t>>>> Courses Registration Order <<<<\n");
        int index = 0;

        for (Course cource : organisedDependencyStructure) {
            System.out.println((++index) + "." + cource);
        }

    }


}
