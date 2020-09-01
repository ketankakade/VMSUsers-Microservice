package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class UserDTO {

	private Integer uuid;
	private String primaryEmailId;
	private String userName;
	private String primaryContactNumber;
	private String alternateContactNumber;
	private Integer signInCount;
	private String displayName;
	private String firstName;
	private String lastName;
	private String avtarUrl;
	private Boolean isBlackListed;
	private Integer failedAttempts;
	private String userCategory;

}
