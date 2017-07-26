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
    // Implementation Notes - notes=" O nota pentru claritate"

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Bianca Culea")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved service, baby !"),
            @ApiResponse(code = 400, message = "Bad Request, baby !"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource, baby !"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden, baby !"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found, baby !"),
            @ApiResponse(code = 500, message = "Failure, baby !")
    }
    )
    //@RequestMapping("/greeting") - genereaza toate metodele, nu doar pe GET
    @RequestMapping(method = RequestMethod.GET, path="/greeting", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:9000")
    public Greeting greeting(@RequestParam(value="name", defaultValue="Bianca Culea") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}

//