package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.LibCategory;
import com.roky.thunderspi.entities.LibElement;
import com.roky.thunderspi.repositories.LibCategoryrepo;
import com.roky.thunderspi.repositories.LibElementRepo;
import com.roky.thunderspi.services.ICourseService;
import com.roky.thunderspi.services.ILibElementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/lib")
@AllArgsConstructor
public class LibElementController {


    private ILibElementService iLibElementService;
@Autowired
    LibElementRepo libElementRepo;
@Autowired
    LibCategoryrepo libCategoryrepo;

    @GetMapping("/getAll")
    public List<LibElement> findAllLibElements() {
        return iLibElementService.findAllLibEle();
    }

    @GetMapping("getAll/{id}")
    public LibElement findLibEleById(@PathVariable Long id) { return iLibElementService.findLibEleById(id);
    }

    @PostMapping("/add/{idcat}")
    public LibElement addLibEle(@RequestBody LibElement libElement,@PathVariable Long idcat) {

        LibCategory libCategory = libCategoryrepo.findById(idcat).orElse(null);
        libElement.setLibElement(libElement);
        return libElementRepo.save(libElement);
    }

    @PutMapping("/update")
    public LibElement editLibElement(@RequestBody LibElement libElement) {
        return iLibElementService.addLibEle(libElement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLibEle(@PathVariable Long id) {
        iLibElementService.deleteLibEle(id);
    }
}
