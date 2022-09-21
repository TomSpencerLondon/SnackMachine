package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SnackController {

  private final SnackService snackService;

  @Autowired
  public SnackController(SnackService snackService) {
    this.snackService = snackService;
  }

  @GetMapping("/")
  public String index(Model model) {
    Money moneyInTransaction = snackService.moneyInserted();
    Money moneyInside = Money.plus(snackService.moneyInside(), snackService.moneyInserted());
    model.addAttribute("machine", SnackMachineView.from(moneyInTransaction, moneyInside));
    return "index";
  }

  @PostMapping("/insert")
  public String insert(@RequestParam(name="coin") String coin) {
    snackService.insert(coin);
    return "redirect:/";
  }

  @PostMapping("/buy")
  public String buy(@RequestParam(name="coin") String coin) {
    snackService.buySnack();
    return "redirect:/";
  }

  @PostMapping("/return")
  public String returnMoney() {
    snackService.returnMoney();
    return "redirect:/";
  }
}