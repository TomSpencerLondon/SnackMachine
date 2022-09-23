package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.Optional;
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
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(1L));
    SnackMachineView snackMachineView = snackMachine.map(SnackMachineView::from)
        .orElseThrow(SnackMachineUnavailable::new);
    model.addAttribute("machine", snackMachineView);
    return "index";
  }

  @PostMapping("/insert")
  public String insert(@RequestParam(name="coin") String coin) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(1L));
    snackMachine.ifPresent(sn -> sn.insertMoney(MoneyView.from(coin)));
    return "redirect:/";
  }

  @PostMapping("/buy")
  public String buy() {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(1L));
    snackMachine.ifPresent(sn -> sn.buySnack(1));
    return "redirect:/";
  }

  @PostMapping("/return")
  public String returnMoney() {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(1L));
    snackMachine.ifPresent(SnackMachine::returnMoney);
    return "redirect:/";
  }
}
