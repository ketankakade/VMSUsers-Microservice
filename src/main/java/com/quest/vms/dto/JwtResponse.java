package com.quest.vms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

	private Long id;
	private String username;
	private String email;
	private String jwt;
	// private List<String> roles;

}
