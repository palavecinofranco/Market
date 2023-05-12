package com.palavecinofranco.market.web.controller;

import com.palavecinofranco.market.domain.dto.PurchaseDTO;
import com.palavecinofranco.market.domain.dto.PurchaseDTOSchema;
import com.palavecinofranco.market.domain.service.PurchaseService;
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

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Controller for purchases operations")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(description = "Get all the purchases made")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<PurchaseDTO>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @Operation(description = "Find all purchases made by a client")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200", description = "Purchases found"),
            @ApiResponse (responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<List<PurchaseDTO>> getByClientId(
            @Parameter(description = "The id of the client", required = true, example = "4546221")
            @PathVariable("id") Long clientId){
        return purchaseService.getByClient(clientId)
                .map(purchaseDTOS -> new ResponseEntity<>(purchaseDTOS, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(description = "Make a purchase",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PurchaseDTOSchema.class))))
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<PurchaseDTO> save(@RequestBody PurchaseDTO purchaseDTO){
        return new ResponseEntity<>(purchaseService.save(purchaseDTO), HttpStatus.CREATED);
    }

}
