package com.example.deliverymanagement.repository;

import com.example.deliverymanagement.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "riders", path = "riders")
public interface RiderRepository extends JpaRepository<Rider, String> {
}