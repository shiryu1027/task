package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	private int id;
	private String name;
	private int age;
	private LocalDate birthday;
}
