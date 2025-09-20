package org.example.builder.itineraryBuilderAssignment;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

public class Itinerary {

    private String travelerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String origin;
    private String destination;
    private List<Segment> segments;
    private List<String> tags;
    private Double budget;
    private Boolean travelInsurance;

    protected static final Logger logger = Logger.getLogger(Itinerary.class.getName());

    private Itinerary(Builder builder) {
        this.travelerName = builder.travelerName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.segments = builder.segments;
        this.tags = builder.tags;
        this.budget = builder.budget;
        this.travelInsurance = builder.travelInsurance;
    }

    public String getTravelerName() {
        return this.travelerName;
    }

    public LocalDate getDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public double getBudget() {
        return this.budget;
    }

    public boolean hasTravelInsurance() {
        return this.travelInsurance;
    }

    public static class Builder {
        private String travelerName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String origin;
        private String destination;
        private List<Segment> segments;
        private List<String> tags;
        private double budget;
        private boolean travelInsurance;

        public Builder travelerName(String travelerName) {
            if (travelerName == null || travelerName.isEmpty()) {
                throw new IllegalArgumentException("Traveler name cannot be null or empty");
            }
            this.travelerName = travelerName;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            if (startDate == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            if (endDate == null)
                throw new IllegalArgumentException("endDate cannot be null.");
            if (endDate.isBefore(this.startDate))
                throw new IllegalArgumentException("endDate " + endDate + " is before startDate." + this.startDate);
            this.endDate = endDate;
            return this;
        }

        public Builder origin(String origin) {
            if (origin == null || origin.length() != 3) {
                throw new IllegalArgumentException("origin must be a 3-letter IATA code: " + origin + " given.");
            }
            this.origin = origin;
            return this;
        }

        public Builder destination(String destination) {
            if (destination == null || destination.length() != 3) {
                throw new IllegalArgumentException("destination must be a 3-letter IATA code: " + destination + " given.");
            }
            this.destination = destination;
            return this;
        }

        public Builder segments(List<Segment> segments) {
            StringBuilder segmentContinuity = new StringBuilder();
            for (int i = 0; i < segments.size(); i++) {
                if (segments.get(i) == null) {
                    throw new IllegalArgumentException("Segment at index " + i + " cannot be null");
                }
                Segment s = new Segment.Builder()
                        .from(segments.get(i).getFrom())
                        .to(segments.get(i).getTo())
                        .departAt(segments.get(i).getDepartAt())
                        .arriveAt(segments.get(i).getArriveAt())
                        .carrier(segments.get(i).getCarrier())
                        .build();
                if (i < segments.size() - 1) {
                    segmentContinuity.append(segments.get(i).getTo()).append("->").append(segments.get(i + 1).getFrom());
                if (!(segments.get(i).getDepartAt().isBefore(segments.get(i + 1).getDepartAt()))) {
                    throw new IllegalArgumentException("Segment at index " + i + " does not depart before next segment at index " + (i + 1));
                }
                if (!(segments.get(i).getTo().equals(segments.get(i + 1).getFrom()))) {
                    logger.severe("Gaps/mismacted location between segments: \" Segments must be continous: " + segmentContinuity + "\"");
                    throw new IllegalArgumentException("Segment at index " + i + " does not has same from and to: " + segments.get(i).getFrom());
                }
                if (!(segments.get(i).getArriveAt().isBefore(segments.get(i + 1).getDepartAt()))) {
                    logger.severe("Disorder in times: \" Segments not in chronological order at index " + i + " (" + segments.get(i + 1).getDepartAt() + " before " + segments.get(i).getArriveAt() + "\"");
                    throw new IllegalArgumentException("Segment at index " + i + " does not arrive before next segment at index " + (i + 1));
                }
                }
                if (!origin.equals(segments.get(0).getFrom())) {
                    throw new IllegalArgumentException("First segment origin must be same as itinerary origin");
                }
                if (!destination.equals(segments.get(segments.size() - 1).getTo())) {
                    throw new IllegalArgumentException("Last segment destination must be same as itinerary destination");
            }
            }
            this.segments = segments;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder budget(double budget) {
            this.budget = budget;
            return this;
        }

        public Builder travelInsurance(boolean travelInsurance) {
            this.travelInsurance = travelInsurance;
            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }

}
