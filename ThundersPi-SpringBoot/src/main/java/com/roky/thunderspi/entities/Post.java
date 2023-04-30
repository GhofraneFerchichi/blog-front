package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    @Column
    private String title;

    @Lob // it means data can take up a lot of space
    @Column
    @NotEmpty
    private String content;


    private String image;
    @JsonIgnore

    @Column
    private Instant created_At;
    @JsonIgnore

    @Column
    private Instant updated_At;

    @JsonIgnore
	  @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	  @JsonManagedReference
	  private FileDB files;
    @ManyToOne
    private User user;

    @Column
    @NotBlank
    private String userName;

    /* @OneToMany(mappedBy = "postcom")
     private List<Comment> comments = new ArrayList<Comment>();

     public List<Comment> getComments() {
         return comments;
     }
 */
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<Comment> comment;
    
    @OneToMany(mappedBy = "postlike",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<PostLike> likes;
    
    @OneToMany(mappedBy = "postdislike",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<PostDislike> dislikes;

   /* @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    Comment comment;
*/
}
