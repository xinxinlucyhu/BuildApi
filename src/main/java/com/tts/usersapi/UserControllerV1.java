package com.tts.usersapi;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;



@RestController
@Api(value="users", description="OPerations to view/create/update/delete users")
@RequestMapping("/v1")
public class UserControllerV1
{
    @Autowired
    UserRepository repository;
 
   
    @ApiOperation(value = "Get all users, optionally filtered by state", response= User.class, responseContainer ="List")
    @ApiResponses( value= { 
            @ApiResponse(code=201, message="Successfully retrieved users"),
            @ApiResponse(code=400, message="Bad request formatting or user exists") 
    })
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value ="state", required=false) String state, @RequestBody @Valid User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);

        }
        repository.findAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Get a single user", response = User.class)
    @GetMapping("/user/{id}")
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="Successfully retrieved users"),
            @ApiResponse(code=404, message="Use wan't found") 
    })
    public ResponseEntity<Optional<User>> getUserById(@PathVariable(value="id") Long id, BindingResult bindingResult)
    { 
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<Optional<User>>(HttpStatus.BAD_REQUEST);
        }
        repository.findById(id);
        return new ResponseEntity<Optional<User>>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Create a user", response=Void.class)
    @ApiResponses( value= { 
            @ApiResponse(code=201, message="Successfully created users"),
            @ApiResponse(code=400, message="Bad request formatting or user exists") 
    })
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user,
                                           BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
    @ApiOperation(value = "Update a user", response=Void.class)
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="User updated successfully"),
            @ApiResponse(code=400, message="Bad request formatting") ,
            @ApiResponse(code=404, message="User id not found") 

    })
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> createUser(@PathVariable(value="id") Long id, @RequestBody @Valid User user
                                           , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    
    @ApiOperation(value = "Delete a user", response=Void.class)
    @DeleteMapping("/users/{id}")
    @ApiResponses( value= { 
            @ApiResponse(code=200, message="User udeleted successfully"),
            @ApiResponse(code=404, message="User id not found") 
    })
    public ResponseEntity<Void> deleteUser(@PathVariable(value="id")Long id, @RequestBody @Valid User user, BindingResult bindingResult) 
    {
       
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
