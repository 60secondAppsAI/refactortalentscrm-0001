package com.refactortalentscrm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.refactortalentscrm.domain.Note;
import com.refactortalentscrm.dto.NoteDTO;
import com.refactortalentscrm.dto.NoteSearchDTO;
import com.refactortalentscrm.dto.NotePageDTO;
import com.refactortalentscrm.dto.NoteConvertCriteriaDTO;
import com.refactortalentscrm.service.GenericService;
import com.refactortalentscrm.dto.common.RequestDTO;
import com.refactortalentscrm.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NoteService extends GenericService<Note, Integer> {

	List<Note> findAll();

	ResultDTO addNote(NoteDTO noteDTO, RequestDTO requestDTO);

	ResultDTO updateNote(NoteDTO noteDTO, RequestDTO requestDTO);

    Page<Note> getAllNotes(Pageable pageable);

    Page<Note> getAllNotes(Specification<Note> spec, Pageable pageable);

	ResponseEntity<NotePageDTO> getNotes(NoteSearchDTO noteSearchDTO);
	
	List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes, NoteConvertCriteriaDTO convertCriteria);

	NoteDTO getNoteDTOById(Integer noteId);







}





