package com.example;

import java.util.concurrent.atomic.AtomicLong;

import io.swagger.annotations.*;


import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="onlinegreetings", description=" Operatiune de salutare hi hi hi !")

public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    //@ApiOperation(value = "View a  hello response",response = Greeting.class)
    @ApiOperation(value = "get greeting - View a  hello response! ", nickname = "getGreeting", notes=" O nota pentru claritate", response = Greeting.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Bianca Culea")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved service"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Failure")
    }
    )
    //@RequestMapping("/greeting") - genereaza toate metodele, nu doar pe GET
    @RequestMapping(method = RequestMethod.GET, path="/greeting", produces = "application/json")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}

//