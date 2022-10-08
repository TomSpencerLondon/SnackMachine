package com.tomspencerlondon.snackmachine.adapter.in.web;

import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.domain.Money;
import com.tomspencerlondon.snackmachine.hexagon.domain.NoSnacksAvailable;
import com.tomspencerlondon.snackmachine.hexagon.domain.NotEnoughMoney;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<SnackMachineView> snackMachineViews = snackService
        .findAll()
        .stream()
        .map(SnackMachineView::from).toList();
    model.addAttribute("machines", snackMachineViews);
    return "index";
  }

  @GetMapping("/snack-machine/{id}")
  public String snackMachine(Model model, @PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));
    SnackMachineView snackMachineView = snackMachine.map(SnackMachineView::from)
        .orElseThrow(SnackMachineUnavailable::new);
    model.addAttribute("machine", snackMachineView);
    return "snack-machine";
  }

  @PostMapping("/snack-machine/{id}/insert")
  public String insert(@RequestParam(name="coin") String coin, @PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));
    snackMachine.ifPresent(sn -> sn.insertMoney(toMoney(coin)));
    return "redirect:/snack-machine/" + id;
  }

  @PostMapping("/snack-machine/{id}/buy")
  public String buy(@RequestParam(name="position") Integer position, @PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));
    try {
      snackMachine.ifPresent(sn -> sn.buySnack(position));
    } catch (NoSnacksAvailable e) {
      return "redirect:/admin/snack-machine/" + id;
    } catch (NotEnoughMoney e) {
      return "redirect:/snack-machine/" + id;
    }
    return "redirect:/snack-machine/" + id;
  }

  @PostMapping("/snack-machine/{id}/return")
  public String returnMoney(@PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));
    snackMachine.ifPresent(SnackMachine::returnMoney);
    return "redirect:/snack-machine/" + id;
  }

  private Money toMoney(String coin) {
    return switch (coin) {
      case "1_C" -> Money.ONE_CENT;
      case "5_C" -> Money.FIVE_CENT;
      case "10_C" -> Money.TEN_CENT;
      case "25_C" -> Money.QUARTER_CENT;
      case "1_D" -> Money.ONE_DOLLAR;
      case "5_D" -> Money.FIVE_DOLLAR;
      case "20_D" -> Money.TWENTY_DOLLAR;
      default -> Money.ZERO;
    };
  }
}
