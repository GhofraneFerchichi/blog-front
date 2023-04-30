package com.roky.thunderspi.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import antlr.collections.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String fname;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonIgnore
	Set<Comment> comments;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="utilis")
	@JsonIgnore
	private Set<PostLike> Likes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="utilis")
	@JsonIgnore
		private Set<PostDislike> dislikes;
	
	



}
