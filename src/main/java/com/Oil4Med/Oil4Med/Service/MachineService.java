package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.Machine;

import java.util.List;

public interface MachineService {
    List<Machine> getMachines();
    Machine getMachineById(Long id);
    Machine addMachine(Machine machine);
    void deleteMachine(Machine machine);

    void updateMachine(Long id, Machine machine);
}
