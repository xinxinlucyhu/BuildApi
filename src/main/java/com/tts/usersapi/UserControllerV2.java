package com.tts.usersapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value="users", description="OPerations to view/create/update/delete users")
@RequestMapping("/v2")
public class UserControllerV2
{
    @Autowired
    UserRepository repository;
 
   
    @ApiOperation(value = "Get all users, optionally filtered by state", response= User.class, responseContainer ="List")
    @ApiResponses( value= { 
            @ApiResponse(code=201, message="Successfully retrieved users"),
            @ApiResponse(code=400, message="Bad request, state parameter missing") 
    })
    @GetMapping("/users")
    public List<User> getUsers(@RequestParam(value ="state", required=false) String state)
    {
        if(state != null)
        {
            return repository.findByState(state);
        }
        return repository.findAll();
    }
    
    @ApiOperation(value = "Get a single user", response = User.class)
    @GetMapping("/user/{id}")
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="Successfully retrieved users"),
            @ApiResponse(code=404, message="Use wan't found") 
    })
    public Optional<User> getUserById(@PathVariable(value="id") Long id)
    { 
        return repository.findById(id);
    }
    
    @ApiOperation(value = "Create a user", response=Void.class)
    @ApiResponses( value= { 
            @ApiResponse(code=201, message="Successfully created users"),
            @ApiResponse(code=400, message="Bad request formatting or user exists") 
    })
    @PostMapping("/users")
    public void createUser(@RequestBody User user)
    {
        repository.save(user);
    }
    
    
    @ApiOperation(value = "Update a user", response=Void.class)
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="User updated successfully"),
            @ApiResponse(code=400, message="Bad request formatting") ,
            @ApiResponse(code=404, message="User id not found") 

    })
    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user)
    {
        repository.save(user);
    }
    
    
    @ApiOperation(value = "Delete a user", response=Void.class)
    @DeleteMapping("/users/{id}")
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="User udeleted successfully"),
            @ApiResponse(code=404, message="User id not found") 
    })
    public void deleteUser(@PathVariable(value="id")Long id) 
    {
        repository.deleteById(id);
    } 
}
