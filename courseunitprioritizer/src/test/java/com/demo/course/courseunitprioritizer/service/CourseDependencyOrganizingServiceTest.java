package com.demo.course.courseunitprioritizer.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.demo.course.courseunitprioritizer.model.Course;

public class CourseDependencyOrganizingServiceTest {

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
    public void shouldReturnValidDependencyOrder() throws FileProcessingServiceException {
        Path coursesFilePath = Paths.get("C:\\Software\\DKA\\courses.csv");
        Path prerequisitesFilePath = Paths.get("C:\\Software\\DKA\\prerequisites.csv");

        Map<Course, List<Course>> courseDependentList = new CourseDependencyHolderFacade(coursesFilePath, prerequisitesFilePath).generate();

        List<Course> courseOrganisedStructure = new CourseDependencyOrganizingService().execute(courseDependentList);

        assertThat(1, is(courseOrganisedStructure.get(0).getCourseId()));
        assertThat("Programming in C", is(courseOrganisedStructure.get(0).getCourseTitle()));

        assertThat(6, is(courseOrganisedStructure.get(1).getCourseId()));
        assertThat("Programming in Java", is(courseOrganisedStructure.get(1).getCourseTitle()));

        assertThat(11, is(courseOrganisedStructure.get(2).getCourseId()));
        assertThat("Scalable Machine Learning", is(courseOrganisedStructure.get(2).getCourseTitle()));

        assertThat(2, is(courseOrganisedStructure.get(3).getCourseId()));
        assertThat("Distributed Computing", is(courseOrganisedStructure.get(3).getCourseTitle()));

        assertThat(7, is(courseOrganisedStructure.get(4).getCourseId()));
        assertThat("Advanced Programming in Java", is(courseOrganisedStructure.get(4).getCourseTitle()));

        assertThat(8, is(courseOrganisedStructure.get(5).getCourseId()));
        assertThat("Big Data with Apache Spark", is(courseOrganisedStructure.get(5).getCourseTitle()));
    }

}
