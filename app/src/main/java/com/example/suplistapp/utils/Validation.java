package com.example.suplistapp.utils;

public class Validation {
  public boolean dateValidation(String date) {
    return date.matches("\\d{2}/\\d{2}/\\d{4}");
  }
}
