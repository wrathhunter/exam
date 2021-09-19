package com.exam.finalexamportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
  @Id
  private String id;
  private EmployeeRoleRelation name;
  public String getAttachedUserIdString() {
	return attachedUserIdString;
}

public void setAttachedUserIdString(String attachedUserIdString) {
	this.attachedUserIdString = attachedUserIdString;
}

private String attachedUserIdString;

  public Role() {

  }

  public Role(EmployeeRoleRelation name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EmployeeRoleRelation getName() {
    return name;
  }

  public void setName(EmployeeRoleRelation name) {
    this.name = name;
  }
}
