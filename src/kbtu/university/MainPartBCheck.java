package kbtu.university;

import kbtu.university.patterns.*;

public class MainPartBCheck {

    public static void main(String[] args) {

        // Singleton
        DataStore ds = DataStore.getInstance();

        // Factory
        var user = UserFactory.createUser("student");
        ds.addUser(user);

        // Strategy
        ReportStrategy report = new MarksStatisticsReport();
        report.generateReport();

        // Observer
        NewsPublisher publisher = new NewsPublisher();
        publisher.notifySubs("New semester started");

        System.out.println("Program finished");
    }
}