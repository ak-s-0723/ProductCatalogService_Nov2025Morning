package org.example.productcatalogservice_nov2025morning.tableInheritanceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value = "MENTOR")
public class Mentor extends User {
    private Double ratings;
}
