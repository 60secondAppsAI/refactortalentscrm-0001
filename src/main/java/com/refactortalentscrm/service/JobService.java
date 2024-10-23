package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.Job;
import com.refactortalentscrm.dto.JobDTO;
import com.refactortalentscrm.dto.JobSearchDTO;
import com.refactortalentscrm.dto.JobPageDTO;
import com.refactortalentscrm.dto.JobConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface JobService extends GenericService<Job, Integer> {

	List<Job> findAll();

	ResultDTO addJob(JobDTO jobDTO, RequestDTO requestDTO);

	ResultDTO updateJob(JobDTO jobDTO, RequestDTO requestDTO);

    Page<Job> getAllJobs(Pageable pageable);

    Page<Job> getAllJobs(Specification<Job> spec, Pageable pageable);

	ResponseEntity<JobPageDTO> getJobs(JobSearchDTO jobSearchDTO);
	
	List<JobDTO> convertJobsToJobDTOs(List<Job> jobs, JobConvertCriteriaDTO convertCriteria);

	JobDTO getJobDTOById(Integer jobId);







}





