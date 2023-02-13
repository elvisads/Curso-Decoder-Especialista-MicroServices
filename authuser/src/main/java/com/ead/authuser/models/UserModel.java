package com.ead.authuser.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) /* Faz com que ao dar um get nesta classe não mostre os campo que estiverem em branco no JSON*/
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@JsonIgnore /* Sempre que formos fazer uma serealização (uma chamada ao serviço)... uma chamada ao serviço, nao expoe os dados para o cliente no JSON */
	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 150)
	private String fullName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 20)
	private String cpf;

	@Column
	private String imageUrl;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy HH:mm:ss")
	private LocalDateTime creationDate;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy HH:mm:ss")
	private LocalDateTime lastUpdateDate;

}
