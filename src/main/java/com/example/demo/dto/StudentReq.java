package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentReq {

  private String name;
  private String matricNumber;
  private String department;
}
