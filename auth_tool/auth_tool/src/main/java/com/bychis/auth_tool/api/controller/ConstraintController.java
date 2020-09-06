package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.api.request_holders.ConstraintRequestHolder;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.service.ConstraintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("policy/role/{roleID}/level/{levelID}/constraint")
@RestController
@CrossOrigin("*")
public class ConstraintController {

    private final ConstraintService constraintService;

    public ConstraintController(ConstraintService constraintService){

        this.constraintService = constraintService;
    }

    @PostMapping
    public void createConstraint(@PathVariable String roleID,
                                 @PathVariable int levelID,
                                 @RequestBody ConstraintRequestHolder constraintRequestHolder){
        this.constraintService.createConstraint(roleID,levelID,constraintRequestHolder);
    }

    @GetMapping
    public List<Constraint> getConstraintsAll(@PathVariable String roleID,
                                              @PathVariable int levelID){
        return this.constraintService.getConstraintsAll(roleID,levelID);
    }

    @GetMapping(path = "/{constraintID}")
    public Constraint getConstraintByID(@PathVariable String roleID,
                                        @PathVariable int levelID,
                                        @PathVariable int constraintID){
        return this.constraintService.getConstraintByID(roleID,levelID,constraintID);
    }

    @DeleteMapping(path = "/{constraintID}")
    public void deleteConstraint(@PathVariable String roleID,
                                 @PathVariable int levelID,
                                 @PathVariable int constraintID){
        this.constraintService.deleteConstraint(roleID,levelID,constraintID);
    }

    @PutMapping(path = "/{constraintID}")
    public void updateConstraint(@PathVariable String roleID,
                                 @PathVariable int levelID,
                                 @PathVariable int constraintID,
                                 @RequestBody ConstraintRequestHolder constraintRequestHolder){

        this.constraintService.updateConstraint(roleID,levelID,constraintID,constraintRequestHolder);
    }
    // @GetMapping()
    // public int getConstraintNumber(@PathVariable String roleID,
    //                               @PathVariable int levelID){
    //  return constraintService.getConstraintNumber(roleID,levelID);
    //  }


}
