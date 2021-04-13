package com.tts.usersapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO
    @ApiModelProperty(notes = "The user id")
    Long id;
    @ApiModelProperty(notes = "The first name of the user")
    @Size(max=20)
    String firstName;

    @ApiModelProperty(notes = "The last name of the user")
    @Size(min=2)
    String lastName;

    @ApiModelProperty(notes = "The state of residence of the user")
    @Size(min=4, max=20)
    String state;
}
