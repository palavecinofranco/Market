package com.palavecinofranco.market.web.controller;


import com.palavecinofranco.market.domain.dto.CustomerDTO;
import com.palavecinofranco.market.domain.dto.CustomerDTOSchema;
import com.palavecinofranco.market.domain.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Controller for customers operations")
public class CustomerController {
    @Autowired
    public CustomerService customerService;

    @GetMapping("/all")
    @Operation(description = "Get all exist customers")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Get Customer by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "CUSTOMER NOT FOUND")
    public ResponseEntity<Optional<CustomerDTO>> getCustomerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }

    @GetMapping("/name/{fullName}")
    @Operation(description = "Get customer by fullname")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "CUSTOMER NOT FOUND")
    public ResponseEntity<Optional<List<CustomerDTO>>> getCustomerByFullName(
            @Parameter(description = "The fullname of the customer(follow the pattern first name + last name)", required = true, example = "Franco Palavecino")
            @PathVariable String fullName){
        String nameAndLastname[] = fullName.split(" ");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getByFullName(nameAndLastname[0], nameAndLastname[1]));
    }

    @PostMapping("/save")
    @Operation(description = "Create a customer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDTOSchema.class))))
    @ApiResponse(responseCode = "201", description = "CUSTOMER CREATED",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTOSchema.class))})
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity(customerService.save(customerDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a customer by id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "CUSTOMER DELETED"),
            @ApiResponse (responseCode = "404", description = "CUSTOMER NOT FOUND")
    })
    public ResponseEntity delete(@PathVariable("id") Long id){
        return new ResponseEntity(customerService.delete(id) ? HttpStatus.OK
                : HttpStatus.NOT_FOUND);
    }

}
