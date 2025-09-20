package org.example.builder.itineraryBuilderAssignment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class Main {

    private List<Segment> segments;
    private List<String> tags;

    public void addSegment(Segment s) {
        segments.add(s);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public Itinerary.Builder weekendGateway(String origin, String destination) {
        LocalDate today = LocalDate.now();
        LocalDate startLocalDate = today.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDate endLocalDate = startLocalDate.plusDays(2);
        Main main = new Main();
        main.addTag("weekend");
        return new Itinerary.Builder()
                .origin(origin)
                .destination(destination)
                .startDate(startLocalDate)
                .endDate(endLocalDate)
                .tags(tags);
    }

    public static void main(String[] args) {
        Itinerary itinerary = new Itinerary.Builder()
                .travelerName("Alice")
                .origin("LHR")
                .destination("CDG")
                .startDate(LocalDate.of(2025,10,01))
                .endDate(LocalDate.of(2025,10,05))
                .build();
    }
}
