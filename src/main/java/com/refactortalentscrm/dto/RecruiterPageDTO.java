package com.refactortalentscrm.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecruiterPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<RecruiterDTO> recruiters;
}





