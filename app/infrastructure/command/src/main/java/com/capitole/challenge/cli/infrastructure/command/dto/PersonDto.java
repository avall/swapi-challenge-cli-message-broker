package com.capitole.challenge.cli.infrastructure.command.dto;

import lombok.Data;

/**
 * @author alex.vall on 2025-Aug-13
 */
@Data
public class PersonDto {
  private String name;
  private int id;
  private String email;
}