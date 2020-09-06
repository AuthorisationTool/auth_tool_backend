package com.bychis.auth_tool.service;

import com.bychis.auth_tool.dao.BluetoothBadgeRepo;
import com.bychis.auth_tool.model.dbEntities.BluetoothBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BluetoothBadgeService {

    private final BluetoothBadgeRepo bluetoothBadgeRepo;

    public BluetoothBadgeService(BluetoothBadgeRepo bluetoothBadgeRepo) {
    this.bluetoothBadgeRepo = bluetoothBadgeRepo;
    }

    public void addBadgeBluetooth(BluetoothBadge bb){
        bluetoothBadgeRepo.save(bb);
    }

    public void deleteBadgeBluetooth(Long id){
        bluetoothBadgeRepo.deleteById(id);
    }

    public void deleteAllBadges(){
        bluetoothBadgeRepo.deleteAll();
    }

    public List<BluetoothBadge> getAllBluetoothBadges()
    { return this.bluetoothBadgeRepo.findAll(); }

    public void updateBadgeBluetoothHolder(Long id,String holder){
        Optional<BluetoothBadge> bb = this.bluetoothBadgeRepo.findById(id);
        BluetoothBadge bb_ = bb.get();
        bb_.setHolder_uid(holder);
        bluetoothBadgeRepo.deleteById(id);
        bluetoothBadgeRepo.save(bb_);
    }

    public BluetoothBadge getBluetoothBadgeByBid(String bid){
        return bluetoothBadgeRepo.getBluetoothBadgeByBid(bid);

    }
}
