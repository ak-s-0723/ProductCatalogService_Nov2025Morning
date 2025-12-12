package org.example.productcatalogservice_nov2025morning.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User {
    private Long numberOfHelpRequests;
}
