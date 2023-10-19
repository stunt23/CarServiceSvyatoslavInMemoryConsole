package org.example.StorageInterf;

import org.example.Entity.Repairer;

import java.util.List;
import java.util.Map;

public interface RepairerStorageInterf {
    long save(Repairer repairer);
    Repairer remove(Long id);
    List<Repairer> findAll();

    Map<Long, Repairer> getRepairers();
}

