package org.example.builder.itineraryBuilderAssignment;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class Segment {

    private String from;
    private String to;
    private LocalDate departAt;
    private LocalDate arriveAt;
    private String carrier;

    private Segment(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.departAt = builder.departAt;
        this.arriveAt = builder.arriveAt;
        this.carrier = builder.carrier;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public LocalDate getDepartAt() {
        return this.departAt;
    }

    public LocalDate getArriveAt() {
        return this.arriveAt;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public static class Builder {
        private String from;
        private String to;
        private LocalDate departAt;
        private LocalDate arriveAt;
        private String carrier;

        public Builder from(String from) {
            if(from == null || from.length() != 3) {
                throw new IllegalArgumentException("From must be a valid 3-letter airport code");
            }
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            if(to == null || to.length() != 3) {
                throw new IllegalArgumentException("From must be a valid 3-letter airport code");
            }
            this.to = to;
            return this;
        }

        public Builder departAt(LocalDate departAt) {
            if (departAt == null) {
                throw new IllegalArgumentException("DepartAt cannot be null");
            }
            this.departAt = departAt;
            return this;
        }

        public Builder arriveAt(LocalDate arriveAt) {
            if (arriveAt == null || arriveAt.isBefore(this.departAt)) {
                throw new IllegalArgumentException("ArriveAt cannot be null or before DepartAt");
            }
            this.arriveAt = arriveAt;
            return this;
        }

        public Builder carrier(String carrier) {
            this.carrier = carrier;
            return this;
        }

        public Segment build() {
            return new Segment(this);
        }
    }

}
