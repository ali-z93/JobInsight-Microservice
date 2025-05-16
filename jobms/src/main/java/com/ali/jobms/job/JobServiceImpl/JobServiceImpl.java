package com.ali.jobms.job.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ali.jobms.job.Job;
import com.ali.jobms.job.JobRepository;
import com.ali.jobms.job.JobService;
import com.ali.jobms.job.dto.JobWithCompanyDTO;
import com.ali.jobms.job.external.Company;

@Service
public class JobServiceImpl implements JobService {

	JobRepository jobRepository;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public List<JobWithCompanyDTO> findAll() {

		List<Job> jobs = jobRepository.findAll();
		List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();

		for (Job job : jobs) {
			JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
			jobWithCompanyDTO.setJob(job);
			Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(),
					Company.class);
			jobWithCompanyDTO.setCompany(company);
			jobWithCompanyDTOs.add(jobWithCompanyDTO);
		}

		return jobWithCompanyDTOs;
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);

	}

	@Override
	public Job getJobByID(long id) {
		// TODO Auto-generated method stub
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteJobById(long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean updateJob(long id, Job updatedJob) {
		// TODO Auto-generated method stub

		Optional<Job> jobOptional = jobRepository.findById(id);
		if (jobOptional.isPresent()) {
			Job job = jobOptional.get();
			job.setTitle(updatedJob.getTitle());
			job.setDescription(updatedJob.getDescription());
			job.setMaxSalary(updatedJob.getMaxSalary());
			job.setMinSalary(updatedJob.getMinSalary());
			job.setLocation(updatedJob.getLocation());
			jobRepository.save(job);
			return true;
		}

		return false;
	}
}
