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
public class CompanyController {

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
    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public List<CompanyDTO> getAllCompanies() {

        List<CompanyDTO> list = companyService.findAll();
        return list;
    }

    /**
     *
     * @param companyDTO
     * @return
     */
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public CompanyDTO addCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.saveCompany(companyDTO);
        return companyDTO;
    }

    /**
     *
     * @param companyDTO
     * @return
     */
    @RequestMapping(value = "/editCompany", method = RequestMethod.POST)
    public CompanyDTO editCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.editCompany(companyDTO);
        return companyDTO;
    }

    /**
     *
     * @param request
     */
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
    public void deleteCompany(HttpServletRequest request) {
        String companyId = request.getParameter("companyId");
        companyService.deleteCompany(Long.valueOf(companyId));
    }

    /**
     *
     * @param companyName
     * @return
     */
    @RequestMapping(value = "/getCompanyByName", method = RequestMethod.GET)
    public CompanyDTO getCompanyByName(@RequestParam("companyName") String companyName) {
        CompanyDTO companyDTO = companyService.findCompanyByName(companyName);
        return companyDTO;
    }


    /**
         *
         * @param
         * @return
         */
        @RequestMapping(value = "/compareCompany",method = RequestMethod.POST)
        public BarChartDTO compareCompany(@RequestBody BarChartDTO barChartDTO) {
//    @RequestMapping(value = "/compareCompany")
//    public BarChartDTO compareCompany() {

//        BarChartDTO barChartDTO = new BarChartDTO();
//        barChartDTO.setFirstCompany("IBN");
        String dataSource ="";

        String firstCompany = barChartDTO.getFirstCompany();
        String secondCompany = barChartDTO.getSecondCompany();

        //display single company Chart
        if (secondCompany == null || "".endsWith(secondCompany.trim()) ) {
            dataSource = dataSourceForSingleCompany(firstCompany);
        }
        //display two company comparison chart
        else{
            dataSource = dataSourceForTwoCompany(firstCompany, secondCompany);
        }

        LOGGER.info("data source ======================>" + dataSource);
        JSONObject dataSourceObj = JSONObject.fromObject(dataSource);
        barChartDTO.setDataSource(dataSourceObj);

        LOGGER.info("barChartDTO.getDataSource()===================================>"+barChartDTO.getDataSource());

        return barChartDTO;
    }


    private String dataSourceForSingleCompany(String companyName) {
        CompanyDTO companyDTO = companyService.findCompanyByName(companyName);
        List<StockPriceDTO> list = stockPriceFeignService.getStockPriceList(companyDTO.getStockCode());

        String barChart = CompareHelper.getBarChart();
        String barCategories = CompareHelper.getBarCategories(list);
        String barDataSet = CompareHelper.getBarDataSetForOneCompany(companyDTO, list);

        String dataSource = "{" + barChart + barCategories + barDataSet + "}";
        return dataSource;
    }

    private String dataSourceForTwoCompany(String firstCompany, String secondCompany) {
        CompanyDTO companyOneDTO = companyService.findCompanyByName(firstCompany);
        CompanyDTO companyTwoDTO = companyService.findCompanyByName(secondCompany);

        List<StockPriceDTO> listOne = stockPriceFeignService.getStockPriceList(companyOneDTO.getStockCode());
        List<StockPriceDTO> listTwo = stockPriceFeignService.getStockPriceList(companyTwoDTO.getStockCode());

        String barChart = CompareHelper.getBarChart();
        String barCategories = "";
        //use the longer time range as the xAxis
        if(listOne.size() > listTwo.size()) {
            barCategories = CompareHelper.getBarCategories(listOne);
        }
        else {
            barCategories = CompareHelper.getBarCategories(listTwo);
        }
        String barDataSet = CompareHelper.getBarDataSetForTwoCompany(companyOneDTO, companyTwoDTO, listOne, listTwo);
        String dataSource = "{" + barChart + barCategories + barDataSet + "}";
        return dataSource;
    }



    @RequestMapping("/getCompanyCodesBySector")
    public List<String> getCompanyCodesBySector(@RequestParam("sectorName") String sectorName)  {
        List<CompanyDTO> companyList = companyService.findCompaniesBySectorName(sectorName);
        List<String> companyCodeList = new ArrayList<String>();
        if(companyList != null && companyList.size() > 0) {
            for(CompanyDTO companyDTO: companyList) {
                companyCodeList.add(companyDTO.getStockCode());
            }
        }
        return companyCodeList;
    }


    @RequestMapping(value = "/testStr")
    public BarChartDTO getDataSource() {

        BarChartDTO barChartDTO = new BarChartDTO();
        String dataSource = "{\"chart\": { \"caption\": \"Company Comparison\", \"xAxisName\": \"Time Range\", \"yAxisName\": \"Price Per Share\", \"theme\": \"fusion\"}, \"categories\": [{  \"category\": [ {\"label\": \"2019-06-07 10:30\"},{\"label\": \"2019-06-08 10:35\"},{\"label\": \"2019-06-08 10:40\"},{\"label\": \"2019-06-08 10:45\"},{\"label\": \"2019-06-08 10:50\"},{\"label\": \"2019-06-08 10:55\"},{\"label\": \"2019-06-08 11:00\"},{\"label\": \"2019-06-08 11:05\"},{\"label\": \"2019-06-10 11:10\"},]  }],\"dataset\": [  {  \"seriesname\": \" IBN\", \"data\": [ { \"value\": \"356.23\" }, { \"value\": \"361.31\" }, { \"value\": \"358.12\" }, { \"value\": \"357.09\" }, { \"value\": \"353.62\" }, { \"value\": \"349.56\" }, { \"value\": \"351.43\" }, { \"value\": \"350.12\" }, { \"value\": \"348.91\" },  ] } ]}";
        barChartDTO.setChartType("barType");
        barChartDTO.setFirstCompany("IBN");
        System.out.println("getDataSource()=============================================================>>><<<<<<<<<<<<<");

        JSONObject jsonObject = JSONObject.fromObject(dataSource);
        barChartDTO.setDataSource(jsonObject);
        return barChartDTO;
    }

}
