package org.example.Storages;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.example.Entity.Repairer;
import org.example.StorageInterf.RepairerStorageInterf;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public enum RepairerStorageEnum implements RepairerStorageInterf {
    REPAIRER_STORAGE {
        private Map<Long, Repairer> repairers = new HashMap();
        private Long currentID = 1l;

        @Override
        public long save(@NonNull Repairer repairer) {
            repairers.put(currentID, repairer);
            repairer.setId(currentID);
            currentID++;
            return currentID - 1;
        }

        @Override
        public Repairer remove(@NonNull Long id) {
            return repairers.remove(id);
        }

        @Override
        public List<Repairer> findAll() {
            return repairers.values().stream().sorted((Comparator.comparingLong(Repairer::getId)).
                    thenComparing(Comparator.comparing(Repairer::getName)).
                    thenComparing(Comparator.comparing(Repairer::getStatus))).toList();
        }

        @Override
        public Map<Long, Repairer> getRepairers() {
            return repairers;
        }

        public void setRepairers(Map<Long, Repairer> repairers) {
            this.repairers = repairers;
        }

        public Long getCurrentID() {
            return currentID;
        }

        public void setCurrentID(Long currentID) {
            this.currentID = currentID;
        }
    }
}
