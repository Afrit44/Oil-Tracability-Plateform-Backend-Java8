package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.DTO.ConsumerDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;
import com.Oil4Med.Oil4Med.Service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/oil4med/consumer/")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/createConsumer")
    public Consumer createConsumer(@RequestBody Consumer consumer) throws Exception {
        return consumerService.addConsumer(consumer);
    }
    @GetMapping("/getConsumer")
    public ConsumerDTO findConsumerById(@RequestParam Long consumerId) {
        return ConsumerDTO.fromEntity(consumerService.getConsumersById(consumerId));
    }
    @GetMapping("/getAllConsumers")
    public List<ConsumerDTO> getAllConsumers(){
        List<Consumer> consumers = consumerService.getConsumers();
        List<ConsumerDTO> consumerDTOS = new ArrayList<>();
        for (Consumer consumer : consumers){
            consumerDTOS.add(ConsumerDTO.fromEntity(consumer));
        }
        return consumerDTOS;
    }
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestParam  Long consumerId,Consumer newConsumer){
        consumerService.updateConsumer(consumerId,newConsumer);
    }
    @DeleteMapping("/deleteConsumer")
    public void deleteConsumer(long consumerId) {
        consumerService.deleteConsumer(consumerService.getConsumersById(consumerId));
    }

    @GetMapping("/purchaseOilFromFarmer")
    public PurchaseOil purchaseOilFromFarmer(Consumer consumer, Farmer farmer, OilProduct oilProduct, double quantity, double price) {
        return consumerService.purchaseOilFromFarmer(consumer,farmer,oilProduct,quantity,price);
    }

    @GetMapping("/purchaseOilFromMill")
    public PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity, double price, Consumer consumer) {
        return consumerService.purchaseOilFromMill(millFactory,oilProduct,quantity,price,consumer);
    }

    @GetMapping("/checkTraceability")
    public OilTraceability checkTraceability(OilProduct oilProduct) {
        return consumerService.checkTraceability(oilProduct);
    }



}
