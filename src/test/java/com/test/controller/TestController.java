package com.test.controller;

import com.company.model.CompanyDTO;
import com.company.model.StockPriceDTO;
import com.company.utils.CompareHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {



    @RequestMapping("/hello")
    public String helloMethod() {
        return "IntelliJ rest service";
    }

    @Test
    public void testCompareHelper() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date1 = "2019-06-08 10:35:00";
        String date2 = "2019-06-08 10:40:00";
        String date3 = "2019-06-08 10:43:00";

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName("IBN");

        List<StockPriceDTO> list = new ArrayList();
        StockPriceDTO stockPriceDTO1 = new StockPriceDTO();
        StockPriceDTO stockPriceDTO2 = new StockPriceDTO();
        StockPriceDTO stockPriceDTO3 = new StockPriceDTO();
        stockPriceDTO1.setDateTime(LocalDateTime.parse(date1,df));
        stockPriceDTO1.setCurrentPrice("361.31");
        stockPriceDTO2.setDateTime(LocalDateTime.parse(date2,df));
        stockPriceDTO2.setCurrentPrice("358.12");
        stockPriceDTO3.setDateTime(LocalDateTime.parse(date3,df));
        stockPriceDTO3.setCurrentPrice("349.56");
        list.add(stockPriceDTO1);
        list.add(stockPriceDTO2);
        list.add(stockPriceDTO3);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(CompareHelper.getBarChart());
        stringBuffer.append(CompareHelper.getBarCategories(list));
        stringBuffer.append(CompareHelper.getBarDataSetForOneCompany(companyDTO, list));
        System.out.println(stringBuffer);
    }
}
