package eu.commision.feedback.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContactType {
  GENERAL,
  MARKETING,
  SUPPORT;

  @JsonCreator
  public static ContactType fromJson(String value) {
    if (value == null) {
      return null;
    }
    return ContactType.valueOf(value.trim().toUpperCase());
  }
}