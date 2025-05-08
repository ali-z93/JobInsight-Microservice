package com.ali.jobms.job;

import java.util.List;

public interface JobService {

	List<Job> findAll();

	void createJob(Job job);
	
	Job getJobByID(long id);

	boolean deleteJobById(long id);

	boolean updateJob(long id, Job updatedJob);
	

}
