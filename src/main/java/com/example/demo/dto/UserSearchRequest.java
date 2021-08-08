package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class UserSearchRequest implements Serializable{
	private int id;
	private String name;
	private int age;
	private LocalDate birthday;
}

/*
 * Serializableによりインスタンス化が出来ないというわけではない。
 * */
