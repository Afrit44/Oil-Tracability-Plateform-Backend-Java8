package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.DTO.ConsumerDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;
import com.Oil4Med.Oil4Med.Service.ConsumerService;
import com.Oil4Med.Oil4Med.Service.FarmerService;
import com.Oil4Med.Oil4Med.Service.OilProductService;
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
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private OilProductService oilProductService;

//    @PostMapping("/createConsumer")
//    public Consumer createConsumer(@RequestBody Consumer consumer) throws Exception {
//        return consumerService.addConsumer(consumer);
//    }
//    @GetMapping("/getConsumer")
//    public ConsumerDTO findConsumerById(@RequestParam Long consumerId) {
//        return ConsumerDTO.fromEntity(consumerService.getConsumersById(consumerId));
//    }
//    @GetMapping("/getAllConsumers")
//    public List<ConsumerDTO> getAllConsumers(){
//        List<Consumer> consumers = consumerService.getConsumers();
//        List<ConsumerDTO> consumerDTOS = new ArrayList<>();
//        for (Consumer consumer : consumers){
//            consumerDTOS.add(ConsumerDTO.fromEntity(consumer));
//        }
//        return consumerDTOS;
//    }
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestParam  Long consumerId,Consumer newConsumer){
        consumerService.updateConsumer(consumerId,newConsumer);
    }
    @DeleteMapping("/deleteConsumer")
    public void deleteConsumer(long consumerId) {
        consumerService.deleteConsumer(consumerService.getConsumersById(consumerId));
    }

    @PostMapping("/purchaseOilFromFarmer")
    public PurchaseOil purchaseOilFromFarmer(Long consumerId, Long farmerId, Long oilProductId, double quantity, double price) {
        return consumerService.purchaseOilFromFarmer(consumerService.getConsumersById(consumerId),
                farmerService.getFarmerById(farmerId), oilProductService.getOilProductById(oilProductId),quantity,price);
    }

    @PostMapping("/purchaseOilFromMill")
    public PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity, double price, Consumer consumer) {
        return consumerService.purchaseOilFromMill(millFactory,oilProduct,quantity,price,consumer);
    }

    @GetMapping("/checkTraceability")
    public OilTraceability checkTraceability(Long oilProductId) {
        return consumerService.checkTraceability(oilProductService.getOilProductById(oilProductId));
    }

}
