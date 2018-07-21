package com.demo.course.courseunitprioritizer.service;

import java.io.File;
import java.io.FileWriter;

public class CourseDependencyTestFactory {

    public static void writeCoursDataToFile(File coursesFile) throws Exception {
        String courseDataString = "id,title\n" +
                "1,Programming in C\n" +
                "2,Distributed Computing\n" +
                "3,Database Systems\n" +
                "4,Algorithms 1\n" +
                "5,Algorithms 2\n" +
                "6,Programming in Java\n" +
                "7,Advanced Programming in Java\n" +
                "8,Big Data with Apache Spark\n" +
                "9,Programming in Perl\n" +
                "10,Probability\n" +
                "11,Scalable Machine Learning\n" +
                "12,Data Structures";

        FileWriter writer = new FileWriter(coursesFile);
        writer.write(courseDataString);
        writer.close();
    }

    public static void writeCoursDataDEpendencyToFile(File prerequisitesFile) throws Exception {
        String courseDataString = "course,prerequisite\n" +
                "2,1\n" +
                "2,6\n" +
                "2,11\n" +
                "3,1\n" +
                "3,6\n" +
                "3,7\n" +
                "3,8";

        FileWriter writer = new FileWriter(prerequisitesFile);
        writer.write(courseDataString);
        writer.close();
    }

}
