package com.jyqdesign.keysbankbackend.controller;

import com.jyqdesign.keysbankbackend.entity.Balance;
import com.jyqdesign.keysbankbackend.entity.Operation;
import com.jyqdesign.keysbankbackend.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/{id}")
    public Balance readBalance(
            @PathVariable long id,
            @RequestParam long year) {
        System.out.println("readBalance of "+id+" for year "+year);
        return balanceService.readBalance(id, year);
    }

    @GetMapping("/full/{id}")
    public Balance readFullBalance(@PathVariable long id) {
        System.out.println("readFullBalance of "+id);
        return balanceService.readFullBalance(id);
    }

    @PutMapping("/{id}")
    public Balance updateBalanceById(
            @PathVariable long id,
            @RequestBody Balance updatedBalance) {
        return balanceService.updateBalanceById(id, updatedBalance);
    }
}
