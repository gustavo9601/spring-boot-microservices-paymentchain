package com.paymentchain.businessdomain.transactions.controllers;

import com.paymentchain.businessdomain.transactions.entities.Transaction;
import com.paymentchain.businessdomain.transactions.entities.dto.TransactionInDTO;
import com.paymentchain.businessdomain.transactions.entities.dto.TransactionOutDTO;
import com.paymentchain.businessdomain.transactions.exceptions.ValidateFieldsException;
import com.paymentchain.businessdomain.transactions.repositories.TransactionRepository;
import com.paymentchain.businessdomain.transactions.validators.ValueNotEqual0Validator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;


    private ModelMapper modelMapper = new ModelMapper();

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);


    @Autowired
    private ValueNotEqual0Validator valueNotEqual0Validator; // Validador manual

    @GetMapping()
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(this.transactionRepository.findAll());
    }

    @GetMapping("/{ibanAccount}")
    public ResponseEntity<List<TransactionOutDTO>> get(@PathVariable String ibanAccount) {
        List<Transaction> transactions = this.transactionRepository
                .findByIbanAccount(ibanAccount);

        if (transactions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.modelMapper.map(transactions, List.class));
    }

    @PostMapping
    public ResponseEntity<TransactionOutDTO> post(@Valid @RequestBody TransactionInDTO input,
                                                  BindingResult bindingResult) {

        this.valueNotEqual0Validator.validate(input, bindingResult); // Validador manual

        Transaction transactionInput = this.modelMapper.map(input, Transaction.class); // ModelMapper

        Transaction transaction = transactionRepository.save(transactionInput);

        TransactionOutDTO transactionOutDTO = this.modelMapper.map(transaction, TransactionOutDTO.class); // ModelMapper

        return ResponseEntity.ok(transactionOutDTO);
    }

    @PostMapping("/full-validations")
    public ResponseEntity<TransactionOutDTO> postFullValidations(@Valid @RequestBody TransactionInDTO input,
                                                                 BindingResult bindingResult) {

        log.info("Errores en postFullValidations:");
        bindingResult.getFieldErrors().forEach(error -> {
            log.info(error.getCode() + "  |  " + error.getField() + ": " + error.getDefaultMessage());
        });
        if (bindingResult.hasErrors()) {
            throw new ValidateFieldsException("Error en los campos", bindingResult.getFieldErrors());
        }

        Transaction transactionInput = this.modelMapper.map(input, Transaction.class); // ModelMapper

        Transaction transaction = transactionRepository.save(transactionInput);

        TransactionOutDTO transactionOutDTO = this.modelMapper.map(transaction, TransactionOutDTO.class); // ModelMapper

        return ResponseEntity.ok(transactionOutDTO);
    }

}
