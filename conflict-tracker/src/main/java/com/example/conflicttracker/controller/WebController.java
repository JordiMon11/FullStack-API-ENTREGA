package com.example.conflicttracker.controller;

import com.example.conflicttracker.dto.ConflictCreateDTO;
import com.example.conflicttracker.dto.ConflictDTO;
import com.example.conflicttracker.model.ConflictStatus;
import com.example.conflicttracker.service.ConflictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador Web para vistas Thymeleaf
 * IMPORTANTE: Es @Controller (NO @RestController)
 */
@Controller
@RequestMapping("/web")
public class WebController {

    private final ConflictService conflictService;

    public WebController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    /**
     * GET /web/conflicts
     * Muestra la lista de conflictos en una tabla HTML
     */
    @GetMapping("/conflicts")
    public String listConflicts(Model model) {
        List<ConflictDTO> conflicts = conflictService.findAll();
        model.addAttribute("conflicts", conflicts);
        return "conflicts"; // Busca templates/conflicts.html
    }

    /**
     * GET /web/conflicts/new
     * Muestra el formulario para crear un nuevo conflicto
     */
    @GetMapping("/conflicts/new")
    public String showCreateForm(Model model) {
        model.addAttribute("conflict", new ConflictCreateDTO());
        model.addAttribute("statuses", ConflictStatus.values());
        return "conflict-form"; // Busca templates/conflict-form.html
    }

    /**
     * POST /web/conflicts
     * Procesa el formulario de creación de conflicto
     */
    @PostMapping("/conflicts")
    public String createConflict(
            @ModelAttribute ConflictCreateDTO conflictDTO,
            RedirectAttributes redirectAttributes) {
        
        try {
            conflictService.create(conflictDTO);
            redirectAttributes.addFlashAttribute("success", 
                "Conflicto creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al crear el conflicto: " + e.getMessage());
        }
        
        return "redirect:/web/conflicts";
    }

    /**
     * GET /web/conflicts/{id}/delete
     * Elimina un conflicto
     */
    @GetMapping("/conflicts/{id}/delete")
    public String deleteConflict(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        
        try {
            conflictService.delete(id);
            redirectAttributes.addFlashAttribute("success", 
                "Conflicto eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al eliminar el conflicto: " + e.getMessage());
        }
        
        return "redirect:/web/conflicts";
    }
}
