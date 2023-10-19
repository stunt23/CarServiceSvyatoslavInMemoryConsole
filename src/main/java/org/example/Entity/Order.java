package org.example.Entity;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.example.EnamStatus.OrderStatus;
import org.example.EnamStatus.RepairerStatus;
import org.example.Storages.OrderStorageEnum;
import org.example.Storages.RepairerStorageEnum;

import java.time.LocalDate;
import java.util.*;

@Data
public class Order {
    private long id;
    private double price;
    private LocalDate openingDate;
    private LocalDate completionDate;
    private OrderStatus status;
    @ToString.Exclude
    private List<Long> assignedRepairersIDs = new ArrayList<>();

    @ToString.Exclude
    RepairerStorageEnum repairerStorageEnum = RepairerStorageEnum.REPAIRER_STORAGE;

    public Order(){
        this.openingDate = LocalDate.now();
        this.status = OrderStatus.CREATED;
    }

    public void assignRepairers(@NonNull Long...ids) {
        this.assignedRepairersIDs.addAll(new ArrayList<>(Arrays.stream(ids).toList()));
        assignedRepairersIDs = new ArrayList<>(assignedRepairersIDs.stream().distinct().toList());
        this.setStatus(OrderStatus.IN_WORK);
        Arrays.stream(ids).forEach(id->repairerStorageEnum.getRepairers().get(id).setStatus(RepairerStatus.IS_ASSIGNED));
    }

    public void completeOrder() {
        this.completionDate = LocalDate.now();
        this.status = OrderStatus.COMPLETED;
        this.getAssignedRepairersIDs().stream().forEach(id->repairerStorageEnum.getRepairers().get(id).setStatus(RepairerStatus.IS_FREE));
        this.assignedRepairersIDs.clear(); // чтобы не хранить их.
    }

    public void cancelOrder(){
        this.status = OrderStatus.CANCELLED;
        this.getAssignedRepairersIDs().stream().forEach(id->repairerStorageEnum.getRepairers().get(id).setStatus(RepairerStatus.IS_FREE));
        this.assignedRepairersIDs.clear(); // чтобы не хранить их.
    }

    public String information(){
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", openingDate=" + openingDate +
                ", completionDate=" + completionDate +
                ", status=" + status +
                ", assignedRepairers=" + assignedRepairersIDs +
                '}';
    }

}
