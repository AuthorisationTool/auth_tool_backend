package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.model.ContextBasedModel.model_structure.ContextBasedAccessControl;
import com.bychis.auth_tool.model.Common.Level;
import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileBasedAccessControl;
import com.bychis.auth_tool.api.request_holders.LevelRequestHolder;
import com.bychis.auth_tool.service.LevelService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("policy/role/{roleID}/level")
@RestController
@CrossOrigin("*")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public void createLevel(@PathVariable String roleID,
                            @RequestBody LevelRequestHolder levelRequestHolder){
        int id = levelRequestHolder.getId();
        String model = levelRequestHolder.getLevelModel();
        Level level = null;

        switch (model){
            case "ContextBasedAccessControl":
                level = new Level(id,new ContextBasedAccessControl());
                break;
            case "ProfileBasedAccessControl":
                level = new Level(id,new ProfileBasedAccessControl());
                break;
        }
        this.levelService.createLevel(roleID,level);
    }

    @GetMapping
    public List<Level> getLevelsAll(@PathVariable String roleID){
        return this.levelService.getLevelsAll(roleID);
    }

    @GetMapping(path = "/{levelID}")
    public Level getLevel(@PathVariable String roleID,
                          @PathVariable int levelID){
        return this.levelService.getLevel(roleID,levelID);
    }

    @PutMapping(path = "/{levelID}")
    public void updateLevel(@PathVariable String roleID,
                            @PathVariable int levelID,
                            @RequestBody LevelRequestHolder levelRequestHolder){
        //Do your things here
    }

    @DeleteMapping(path = "/{levelID}")
    public void deleteLevel(@PathVariable String roleID,
                            @PathVariable int levelID){
        this.levelService.deleteLevel(roleID,levelID);
    }

}
