package com.startaideia.vuttr.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.startaideia.vuttr.domain.model.Tool;
import com.startaideia.vuttr.domain.repository.ToolRepository;

@Service
public class ToolService {
	
	@Autowired
	private ToolRepository toolRepository;
	
	public List<Tool> filterByTag(String tag){
		
		List<Tool> toolsbytag = new ArrayList<>();
		List<Tool> tools = toolRepository.findAll();
		
		if(!tools.isEmpty()){
			for(Tool tool : tools) {
				if(tool.getListtags() != null && tool.getListtags().contains(tag)) {
					toolsbytag.add(tool);
				}
			}
		}
		return toolsbytag;
	}
	
	public Tool findById(Long id) {		
		return toolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found! specified value does not exist."));
	}

}
