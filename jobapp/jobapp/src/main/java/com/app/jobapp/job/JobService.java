package com.app.jobapp.job;

import java.util.List;

public interface JobService
{
    List<Job> listAll();

    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJobId(Long id, Job updateBody);
}

