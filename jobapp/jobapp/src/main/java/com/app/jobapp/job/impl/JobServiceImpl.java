package com.app.jobapp.job.impl;

import com.app.jobapp.job.Job;
import com.app.jobapp.job.JobRespositroy;
import com.app.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService
{
    private long nextId = 1L;
    //private List<Job> jobs = new ArrayList<>(); Commented to add JPA base methods
    private final JobRespositroy jobRespositroy;

    public JobServiceImpl(JobRespositroy jobRespositroy)
    {
        this.jobRespositroy = jobRespositroy;
    }

    //Get Method to findAll
    @Override
    public List<Job> listAll()
    {
        return jobRespositroy.findAll();
    }

    //Post Method to add
    @Override
    public void createJob(Job job)
    {
        job.setId(nextId++);
        jobRespositroy.save(job);

    }
    //Get Method to find by ID
    @Override
    public Job getJobById(Long id)
    {
        /*for (Job job : jobs)
        {
            if (job.getId().equals(id))
                return job;
        }*/

        return jobRespositroy.findById(id).orElse(null);
    }
    //Delete method by Id
    @Override
    public boolean deleteJobById(Long id)
    {
        /*Iterator<Job> it = jobs.iterator();
        while (it.hasNext())
        {
            Job job = it.next();
            if (job.getId().equals(id))
            {
                it.remove();
                return true;
            }

        }*/
        try
        {
            jobRespositroy.deleteById(id);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
    //Put method
    @Override
    public boolean updateJobId(Long id, Job updateBody)
    {
        /*for (Job job : jobs)
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
        }*/
        Optional<Job> joboptional = jobRespositroy.findById(id);
        if (joboptional.isPresent())
        {
            Job job=joboptional.get();
            job.setDescription(updateBody.getDescription());
            job.setLocation(updateBody.getLocation());
            job.setMaxsalary(updateBody.getMaxsalary());
            job.setMinsalary(updateBody.getMinsalary());
            job.setTitle(updateBody.getTitle());
            jobRespositroy.save(job);
            return true;
        }
        System.out.println("in put2");
        return false;
    }
}
