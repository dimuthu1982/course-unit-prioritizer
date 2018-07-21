package com.demo.course.courseunitprioritizer.service;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourseDependencyHolderFacadeTest {

    @Rule
    public TemporaryFolder courseFolder = new TemporaryFolder();

    @Rule
    public TemporaryFolder coursesPrerequisitesFolder = new TemporaryFolder();

    private File coursesFile;
    private File prerequisitesFile;

    @Before
    public void setUp() throws Exception {
        coursesFile = courseFolder.newFile("courses.csv");
        prerequisitesFile = coursesPrerequisitesFolder.newFile("prerequisites.csv");

        CourseDependencyTestFactory.writeCoursDataToFile(coursesFile);
        CourseDependencyTestFactory.writeCoursDataDEpendencyToFile(prerequisitesFile);
    }

    @Test
    public void shouldReturnDependentCoursesFirstCourse() throws Exception {

        Path courseFilePath = Paths.get(coursesFile.getPath());
        Path coursePrerequisitePath = Paths.get(prerequisitesFile.getPath());

        Map<Course, List<Course>> courseDependentMap =
                new CourseDependencyHolderFacade(courseFilePath, coursePrerequisitePath).generate();

        List<Course> mainCourseList = new ArrayList<>(courseDependentMap.keySet());

        assertThat(2, is(mainCourseList.get(0).getCourseId()));
        assertThat("Distributed Computing", is(mainCourseList.get(0).getCourseTitle()));

        List<Course> dependenctCourses = courseDependentMap.get(mainCourseList.get(0));

        assertThat(1, is(dependenctCourses.get(0).getCourseId()));
        assertThat("Programming in C", is(dependenctCourses.get(0).getCourseTitle()));

        assertThat(6, is(dependenctCourses.get(1).getCourseId()));
        assertThat("Programming in Java", is(dependenctCourses.get(1).getCourseTitle()));

        assertThat(11, is(dependenctCourses.get(2).getCourseId()));
        assertThat("Scalable Machine Learning", is(dependenctCourses.get(2).getCourseTitle()));
    }

    @Test
    public void shouldReturnDependentCoursesSecondCourse() throws Exception {

        Path courseFilePath = Paths.get(coursesFile.getPath());
        Path coursePrerequisitePath = Paths.get(prerequisitesFile.getPath());

        Map<Course, List<Course>> courseDependentMap =
                new CourseDependencyHolderFacade(courseFilePath, coursePrerequisitePath).generate();

        List<Course> mainCourseList = new ArrayList<>(courseDependentMap.keySet());

        assertThat(3, is(mainCourseList.get(1).getCourseId()));
        assertThat("Database Systems", is(mainCourseList.get(1).getCourseTitle()));

        List<Course> dependenctCourses = courseDependentMap.get(mainCourseList.get(1));

        assertThat(1, is(dependenctCourses.get(0).getCourseId()));
        assertThat("Programming in C", is(dependenctCourses.get(0).getCourseTitle()));

        assertThat(6, is(dependenctCourses.get(1).getCourseId()));
        assertThat("Programming in Java", is(dependenctCourses.get(1).getCourseTitle()));

        assertThat(7, is(dependenctCourses.get(2).getCourseId()));
        assertThat("Advanced Programming in Java", is(dependenctCourses.get(2).getCourseTitle()));

        assertThat(8, is(dependenctCourses.get(3).getCourseId()));
        assertThat("Big Data with Apache Spark", is(dependenctCourses.get(3).getCourseTitle()));
    }

}
