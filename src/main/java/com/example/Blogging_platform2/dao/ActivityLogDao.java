package com.example.Blogging_platform2.dao;

import com.example.Blogging_platform2.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLogDao extends JpaRepository<ActivityLog, Long> {

    // Find all logs for a given user
    List<ActivityLog> findByUserId(Long userId);

    // Find all logs for a given action
    List<ActivityLog> findByAction(String action);


}
