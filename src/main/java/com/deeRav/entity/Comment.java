package com.deeRav.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="comments")
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @CreationTimestamp
    private Date addedDate;
    @UpdateTimestamp
    private Date updatedDate;



}
