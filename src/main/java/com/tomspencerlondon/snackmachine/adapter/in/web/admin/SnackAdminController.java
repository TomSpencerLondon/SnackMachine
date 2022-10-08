package com.tomspencerlondon.snackmachine.adapter.in.web.admin;

import com.tomspencerlondon.snackmachine.adapter.in.web.SnackMachineUnavailable;
import com.tomspencerlondon.snackmachine.adapter.in.web.SnackMachineView;
import com.tomspencerlondon.snackmachine.hexagon.application.SnackService;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachine;
import com.tomspencerlondon.snackmachine.hexagon.domain.SnackMachineId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SnackAdminController {

  private final SnackService snackService;

  @Autowired
  public SnackAdminController(SnackService snackService) {
    this.snackService = snackService;
  }

  @GetMapping("/admin/snack-machine/{id}")
  public String admin(Model model, @PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));
    SnackMachineView snackMachineView = snackMachine.map(SnackMachineView::from)
        .orElseThrow(SnackMachineUnavailable::new);
    model.addAttribute("machine", snackMachineView);
    return "admin";
  }

  @PostMapping("/admin/snack-machine/{id}/add-snack")
  public String addSnack(@RequestParam(name="snack") String snack, @PathVariable("id") Long id) {
    Optional<SnackMachine> snackMachine = snackService.findById(SnackMachineId.of(id));

    snackMachine.ifPresent(sn -> sn.addSnack(snack));

    return "redirect:/admin/snack-machine/" + id;
  }

}
