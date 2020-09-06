package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.model.dbEntities.BluetoothBadge;
import com.bychis.auth_tool.service.BluetoothBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/badges")
@RestController
@CrossOrigin("*")
public class BluetoothBadgeController {
    private final BluetoothBadgeService bluetoothBadgeService;

    @Autowired
    public BluetoothBadgeController(BluetoothBadgeService bluetoothBadgeService){
        this.bluetoothBadgeService = bluetoothBadgeService;
    }

    @GetMapping
    public List<BluetoothBadge> getBluetoothBadgesAll(){
        return this.bluetoothBadgeService.getAllBluetoothBadges();
    }

    @PostMapping
    public void AddBluetoothBadge(@RequestBody BluetoothBadge bluetoothBadge){
        bluetoothBadgeService.addBadgeBluetooth(bluetoothBadge);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBluetoothBadge(@PathVariable Long id){

        this.bluetoothBadgeService.deleteBadgeBluetooth(id);
    }

    @DeleteMapping
    public void deleteAllBadge(){
        this.bluetoothBadgeService.deleteAllBadges();
    }

    @PutMapping(path = "/{id}")
    public void updateBadgeHolder(@PathVariable Long id,@RequestBody String holder){
        bluetoothBadgeService.updateBadgeBluetoothHolder(id,holder);
    }

    @GetMapping("/bid/{bid}")
    public BluetoothBadge getBluetoothBadgeByBid(@PathVariable String bid){
        return bluetoothBadgeService.getBluetoothBadgeByBid(bid);
    }



}
