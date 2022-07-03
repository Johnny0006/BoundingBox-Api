package api.controller;


import api.entity.BoundingBox;
import api.exception.InvalidArgumentException;
import api.service.BoundingBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("boxes")
public class BoundingBoxController {
    private final BoundingBoxService service;

    @Autowired
    public BoundingBoxController(BoundingBoxService service){
        this.service=service;
    }

    @GetMapping("{id}")
    public ResponseEntity<BoundingBox> getBox(@PathVariable long id){
        return service.find(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BoundingBox>> getBoxes(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> postBox(@RequestBody BoundingBox boundingBox) throws InvalidArgumentException {
        service.create(boundingBox);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(boundingBox.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putBox(@RequestBody BoundingBox boundingBox, @PathVariable long id) throws InvalidArgumentException {
        Optional<BoundingBox> box = service.find(id);
        if(box.isPresent()){
            service.update(box.get(),boundingBox);
            return ResponseEntity.accepted().build();
        }else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBox(@PathVariable long id){
        Optional<BoundingBox> box = service.find(id);
        if(box.isPresent()) {
            service.delete(box.get());
            return ResponseEntity.accepted().build();
        }else return ResponseEntity.notFound().build();

    }

}
