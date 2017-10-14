package io.sid.api.controller;

import io.sid.api.entity.User;
import io.sid.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find All Users", notes = "Returns all the Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")

    })
    public List<User> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Find User By Id", notes = "Returns User with specific ID if exists..")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")

    })
    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User findOne(@RequestParam String id) {
        return service.findOne(id);
    }

    @ApiOperation(value = "Create an User", notes = "Creates an User if doesn't exist and Returns the User from DB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")

    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @ApiOperation(value = "Update the User By Id", notes = "Update and Return the User with specific ID if exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")

    })
    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@RequestParam String id, @RequestBody User user) {
        return service.update(id, user);
    }

    @ApiOperation(value = "Delete User By Id", notes = "Delete a User with specific ID if exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")

    })
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@RequestParam String id) {
        service.delete(id);
    }
}
