package com.springboot.insurance_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.insurance_app.dto.ResponseMessageDto;
import com.springboot.insurance_app.exception.ResourceNotFoundException;
import com.springboot.insurance_app.model.Policy;
import com.springboot.insurance_app.model.PolicyHolder;
import com.springboot.insurance_app.service.PolicyHolderService;

@RestController
public class PolicyHolderController {
@Autowired
private PolicyHolderService policyHolderService;
@PostMapping("/holder/add")
public PolicyHolder addHolder(@RequestBody PolicyHolder policyHolder) {
	return policyHolderService.insert(policyHolder);
}

@GetMapping("/holder/showall")
public List<PolicyHolder> getAllholder() {
	List<PolicyHolder> list = policyHolderService.getAllholder();
	return list;
}
@DeleteMapping("/holder/delete/{id}")
public ResponseEntity<?> deletePolicy(@PathVariable int id, ResponseMessageDto dto) {
	
	try {
		policyHolderService.validate(id);
		policyHolderService.delete(id);
	} catch (ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	} 
	dto.setMsg("holder deleted");
	return ResponseEntity.ok(dto);
	
}

@PutMapping("/holder/update/{id}")
public ResponseEntity<?> updateHolder(@PathVariable int id, @RequestBody PolicyHolder newPolicyHolder,ResponseMessageDto dto) {
	try {
	
		PolicyHolder existingPolicyHolderDb=policyHolderService.validate(id);
		if(newPolicyHolder.getName()!= null)
			existingPolicyHolderDb.setName(newPolicyHolder.getName());
		if(newPolicyHolder.getAge() != null)
			existingPolicyHolderDb.setAge(newPolicyHolder.getAge());
		if(newPolicyHolder.getContact()!=null)
			existingPolicyHolderDb.setContact(newPolicyHolder.getContact());
		if(newPolicyHolder.getPanNumber()!=null)
			existingPolicyHolderDb.setPanNumber(newPolicyHolder.getPanNumber());
		existingPolicyHolderDb=policyHolderService.insert(existingPolicyHolderDb);
		return ResponseEntity.ok(existingPolicyHolderDb);
	}catch(ResourceNotFoundException e) {
		dto.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(dto);
	}
	}

@PostMapping("/holder/addBatch")
public List<PolicyHolder> addHolders(@RequestBody List<PolicyHolder> holders) {
    return policyHolderService.insertAll(holders);
}
}
