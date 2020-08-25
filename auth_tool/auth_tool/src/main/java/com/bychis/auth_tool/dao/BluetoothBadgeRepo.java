package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.dbEntities.BluetoothBadge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BluetoothBadgeRepo extends JpaRepository<BluetoothBadge, Long> {
    BluetoothBadge getBluetoothBadgeByBid(String bid);
    default String getHolderUidByBid(String bid){
        BluetoothBadge bluetoothBadge = getBluetoothBadgeByBid(bid);
        return bluetoothBadge.getHolder_uid();
    }
}
