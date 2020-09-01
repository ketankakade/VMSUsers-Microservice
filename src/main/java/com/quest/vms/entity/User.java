package com.quest.vms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	public static final String USER_ID = "uuid";
	public static final String USER_NAME = "userName";
	public static final String PRIMARY_EMAIL_ID = "primaryEmailId";
	public static final String PRIMARY_CONTACT_NUMBER = "primaryContactNumber";
	public static final String ALTERNATE_CONTACT_NUMBER = "alternateContactNumber";
	public static final String SIGNIN_COUNT = "signInCount";
	public static final String DISPLAY_NAME = "displayName";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String AVTAR_URL = "avtarUrl";
	public static final String LAST_LOGIN_AT = "lastLoginAt";
	public static final String IS_BLACK_LISTED = "isBlackListed";
	public static final String FAILED_ATTEMPTS = "failedAttempts";
	public static final String USER_CATEGORY = "userCategory";
	public static final String CREATED_TIMESTAMP = "createdTs";
	public static final String UPDATED_TIMESTAMP = "updatedTs";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = USER_ID)
	private Integer uuid;

	@Column(name = FIRST_NAME, nullable = false)
	private String firstName;

	@Column(name = LAST_NAME, nullable = false)
	private String lastName;
	
	@Column(name = USER_NAME, nullable = false)
	private String userName;

	@Column(name = PRIMARY_EMAIL_ID, nullable = false)
	private String primaryEmailId;

	@Column(name = PRIMARY_CONTACT_NUMBER, length = 10, nullable = false)
	private String primaryContactNumber;

	@Column(name = ALTERNATE_CONTACT_NUMBER)
	private String alternateContactNumber;

	@Column(name = SIGNIN_COUNT)
	private Integer signInCount;

	@CreationTimestamp
	@Column(name = CREATED_TIMESTAMP)
	private Timestamp createdTs;

	@UpdateTimestamp
	@Column(name = UPDATED_TIMESTAMP)
	private Timestamp updatedTs;

	@Column(name = DISPLAY_NAME)
	private String displayName;

	@Column(name = AVTAR_URL)
	private String avtarUrl;

	@Column(name = LAST_LOGIN_AT)
	private Timestamp lastLoginAt;
	
	@Column(name = IS_BLACK_LISTED)
	private Integer isBlackListed;
	
	@Column(name = FAILED_ATTEMPTS)
	private Integer failedAttempts;
	
	@Column(name = USER_CATEGORY)
	private String userCategory;


}
