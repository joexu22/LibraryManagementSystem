package com.gcit.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author joe
 *
 */

@Entity
@Table(name="tbl_library_branch", catalog="library")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="branchName", scope=Branch.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer branchId;
	
	@NotEmpty
	@Length(max=45)	
	private String branchName;

	@NotEmpty
	@Length(max=45)
	private String branchAddress;

	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}
