package com.company.service;

import com.company.model.StockPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-import")
public interface StockPriceFeignService {

    @RequestMapping(value = "/getStockPriceList", method= RequestMethod.GET)
    public List<StockPriceDTO> getStockPriceList(@RequestParam("stockCode") String stockCode);
}