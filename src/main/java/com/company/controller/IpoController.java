package com.company.controller;

import com.company.model.BarChartDTO;
import com.company.model.CompanyDTO;
import com.company.model.IPODetailsDTO;
import com.company.model.StockPriceDTO;
import com.company.service.CompanyService;
import com.company.service.IPODetailsService;
import com.company.service.StockPriceFeignService;
import com.company.utils.CompareHelper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(maxAge = 3600)
@RestController
public class IpoController {

    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private IPODetailsService ipoDetailsService;

    @Autowired
    private StockPriceFeignService stockPriceFeignService;


    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAllIPODetails", method = RequestMethod.GET)
    public List<IPODetailsDTO> getAllIPODetails() {
        List<IPODetailsDTO> list = ipoDetailsService.findAll();
        return list;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getIPOsByCompanyName", method = RequestMethod.GET)
    public List<IPODetailsDTO> getIPOsByCompanyName(@RequestParam("companyName") String companyName) {
        List<IPODetailsDTO> list = ipoDetailsService.getAllIpoDetailsByCompanyName(companyName);
        return list;
    }

    /**
     *
     * @param ipoDetailsDTO
     * @return
     */
    @RequestMapping(value = "/addIPODetails", method = RequestMethod.POST)
    public IPODetailsDTO addIPODetails(@RequestBody IPODetailsDTO ipoDetailsDTO) {
        ipoDetailsService.saveIPODetails(ipoDetailsDTO);
        return ipoDetailsDTO;
    }

    /**
     *
     * @param ipoDetailsDTO
     * @return
     */
    @RequestMapping(value = "/editIPODetails", method = RequestMethod.POST)
    public IPODetailsDTO editIPODetails(@RequestBody IPODetailsDTO ipoDetailsDTO) {
        ipoDetailsService.editIPODetails(ipoDetailsDTO);
        return ipoDetailsDTO;
    }

    /**
     *
     * @param request
     */
    @RequestMapping(value = "/deleteIPODetails", method = RequestMethod.GET)
    public void deleteIPODetails(HttpServletRequest request) {
        String ipoDetailsId = request.getParameter("ipoDetailsId");
        ipoDetailsService.deleteIPODetails(Long.valueOf(ipoDetailsId));
    }


}
