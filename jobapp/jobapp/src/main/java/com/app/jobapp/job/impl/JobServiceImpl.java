package com.app.jobapp.job.impl;

import com.app.jobapp.job.Job;
import com.app.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService
{
    private long nextId = 1L;
    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> listAll()
    {
        return jobs;
    }

    @Override
    public void createJob(Job job)
    {
        job.setId(nextId++);
        jobs.add(job);

    }

    @Override
    public Job getJobById(Long id)
    {
        for (Job job : jobs)
        {
            if (job.getId().equals(id))
                return job;
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id)
    {
        Iterator<Job> it = jobs.iterator();
        while (it.hasNext())
        {
            Job job = it.next();
            if (job.getId().equals(id))
            {
                it.remove();
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean updateJobId(Long id, Job updateBody)
    {
        for (Job job : jobs)
        {
            if (job.getId().equals(id))
            {
                job.setDescription(updateBody.getDescription());
                job.setLocation(updateBody.getLocation());
                job.setMaxsalary(updateBody.getMaxsalary());
                job.setMinsalary(updateBody.getMinsalary());
                job.setTitle(updateBody.getTitle());
                return true;
            }
        }
        return false;
    }
}
