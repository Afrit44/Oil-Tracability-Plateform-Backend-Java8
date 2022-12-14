package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.Machine;
import com.Oil4Med.Oil4Med.Repository.MachineRepository;
import com.Oil4Med.Oil4Med.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineImpl implements MachineService {
    @Autowired
    MachineRepository machineRepository;

    public MachineImpl(MachineRepository machineRepository){
        this.machineRepository=machineRepository;
    }
    @Override
    public List<Machine> getMachines() {
        List<Machine> machines = new ArrayList<>();
        machineRepository.findAll().forEach(machines::add);
        return machines;
    }

    @Override
    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).get();
    }

    @Override
    public Machine addMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public void deleteMachine(Machine machine) {
        machineRepository.delete(machine);
    }

    @Override
    public void updateMachine(Long id, Machine machine) {
        Machine machineFromDb = machineRepository.findById(id).get();
        machineFromDb.setMachineId(machine.getMachineId());
        machineFromDb.setMarque(machine.getMarque());
        machineFromDb.setConstructor(machine.getConstructor());
        machineFromDb.setPurchaseDate(machine.getPurchaseDate());
        machineRepository.save(machineFromDb);
    }
}
