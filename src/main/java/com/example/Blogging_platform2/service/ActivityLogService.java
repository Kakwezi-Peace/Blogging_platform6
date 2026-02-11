package com.example.Blogging_platform2.service;

import com.example.Blogging_platform2.dao.ActivityLogDao;
import com.example.Blogging_platform2.exception.ActivityLogNotFoundException;
import com.example.Blogging_platform2.model.ActivityLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityLogService {
    private final ActivityLogDao activityLogDao;

    public ActivityLogService(ActivityLogDao activityLogDao) {
        this.activityLogDao = activityLogDao;
    }
    public ActivityLog saveLog(ActivityLog log) {
        return activityLogDao.save(log);
    }

    public List<ActivityLog> getAllLogs() {
        return activityLogDao.findAll();
    }

    public Optional<ActivityLog> getLogById(Long id) {
        return activityLogDao.findById(id);
    }

    public void deleteLog(Long id) {
        if (!activityLogDao.existsById(id)) {
            throw new ActivityLogNotFoundException(id);
        }
        activityLogDao.deleteById(id);
    }

    public ActivityLog getLog(Long id) {
        return activityLogDao.findById(id)
                .orElseThrow(() -> new ActivityLogNotFoundException(id));
    }

    public ActivityLog createLog(ActivityLog log) {
        return activityLogDao.save(log);
    }

    public Boolean deleteLogReturnBoolean(Long id) {
        if (!activityLogDao.existsById(id)) {
            throw new ActivityLogNotFoundException(id);
        }
        activityLogDao.deleteById(id);
        return true;
    }
}
