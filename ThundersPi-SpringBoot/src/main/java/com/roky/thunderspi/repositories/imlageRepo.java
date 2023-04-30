package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Image;
import com.roky.thunderspi.entities.Leaderboards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imlageRepo  extends JpaRepository<Image,Long> {
}
