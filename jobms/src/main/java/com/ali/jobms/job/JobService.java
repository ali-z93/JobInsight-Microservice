package com.ali.jobms.job;

import java.util.List;

import com.ali.jobms.job.dto.JobWithCompanyDTO;

public interface JobService {

	List<JobWithCompanyDTO> findAll();

	void createJob(Job job);
	
	Job getJobByID(long id);

	boolean deleteJobById(long id);

	boolean updateJob(long id, Job updatedJob);
	

}
