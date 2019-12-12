package com.company.utils;

import com.company.model.CompanyDTO;
import com.company.model.StockPriceDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompareHelper {


    /**
     *
     * @return
     * "chart": { "caption": "Company Comparison", "xAxisName": "Time Range", "yAxisName": "Price Per Share", "theme": "fusion"},
     */
    public static String getBarChart() {

        return "\"chart\": { \"caption\": \"Company Comparison\", \"xAxisName\": \"Time Range\", \"yAxisName\": \"Price Per Share\", \"theme\": \"fusion\"}, ";
        //return "\"chart\": { \"caption\": \"Company Comparison\", \"xAxisName\": \"Time Range\", \"yAxisName\": \"Price Per Share\", \"theme\": \"fusion\"}, ";
    }

    //"categories": [{
    //                "category": [{"label": "10:00"},{"label": "10:05"},{"label": "10:10"},{"label": "10:10"}]
    //                    }],
    public static String getBarCategories(List<StockPriceDTO> list) {
        //[ {"label": "10:00"},{"label": "10:05"},{"label": "10:10"},{"label": "10:10"} ]
        StringBuffer categories = new StringBuffer("\"categories\": [{  \"category\": ");

        categories.append(categoriesList(list));

        categories.append("  }],");
        return categories.toString();
    }


    /**
     * "dataset": [
     *             {
     *                 "seriesname": "IBN Stock", "data": [
     *                 "data": [
     *                     {
     *                         "value": "6000"
     *                     },
     *                     {
     *                         "value": "19700"
     *                     }
     *                 ]
     *             },
     *             {
     *                 "seriesname": "IDD Stock",
     *                 "data": [
     *                     {
     *                         "value": "19000"
     *                     },
     *                     {
     *                         "value": "14300"
     *                     }
     *                 ]
     *             }
     *         ]
     * @param companyDTO
     * @param list
     * @return
     */
//    public static String getBarDataSet(CompanyDTO companyDTO, List<StockPriceDTO> list) {
//        StringBuffer dataSet = new StringBuffer("\"dataset\": [  {  \"seriesname\": \"" + companyDTO.getCompanyName() + "\", \"data\": [ ");
//
//
//        String valueList = dataSetList(list);
//        dataSet.append(valueList);
//
//        dataSet.append(" ] } ]");
//        return dataSet.toString();
//    }

    public static String getBarDataSetForOneCompany(CompanyDTO companyDTO , List<StockPriceDTO> list) {
        StringBuffer dataSet = new StringBuffer("");
        dataSet.append("\"dataset\": [ ");
        dataSet.append(dataSetList(companyDTO, list));
        dataSet.append(" ]");
        return dataSet.toString();
    }

    public static String getBarDataSetForTwoCompany(CompanyDTO companyOneDTO , CompanyDTO companyTwoDTO, List<StockPriceDTO> listOne, List<StockPriceDTO> listTwo) {
        StringBuffer dataSet = new StringBuffer("");
        dataSet.append("\"dataset\": [ ");
        dataSet.append(dataSetList(companyOneDTO, listOne));
        dataSet.append(dataSetList(companyTwoDTO, listTwo));
        dataSet.append(" ]");
        return dataSet.toString();
    }
    /**
     *     {
     *     "value": "19000"
     *      },
     *     {
     *      "value": "14300"
     *     }
     * @param list
     * @return
     */
    static String dataSetList(CompanyDTO companyDTO , List<StockPriceDTO> list) {
        StringBuffer valueList = new StringBuffer("");
        valueList.append("{\"seriesname\": \"" + companyDTO.getCompanyName() + "\", \"data\": [ ");

        for(StockPriceDTO stockPriceDTO: list) {
            valueList.append("{ \"value\": \"" + stockPriceDTO.getCurrentPrice() + "\" }, ");
        }
        valueList.append("] },");

        return valueList.toString();
    }



    /**
     *
     * [{"label": "10:00"},{"label": "10:05"},{"label": "10:10"},{"label": "10:10"}]
     *
     * @param list
     * @return
     */
    static String categoriesList(List<StockPriceDTO> list) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuffer categoriesList = new StringBuffer("[ ");

        for(StockPriceDTO stockPriceDTO: list) {
            categoriesList.append("{\"label\": ");
            categoriesList.append("\"" + df.format(stockPriceDTO.getDateTime()) + "\"");
            categoriesList.append("},");
        }

        categoriesList.append("]");
        return categoriesList.toString();
    }
}
