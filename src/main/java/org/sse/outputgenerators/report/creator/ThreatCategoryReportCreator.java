package org.sse.outputgenerators.report.creator;

import org.sse.design.model.ThreatCategory;
import org.sse.outputgenerators.report.model.ThreatReport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ThreatCategoryReportCreator {

    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatCategoryReportCreator(HashMap<String, ThreatCategory> threatCategoryHashMap) {
        this.threatCategoryHashMap = threatCategoryHashMap;
    }

    /**
     *
     *
     * @return
     */
    public ThreatReport generateReport(String reportName){

        ThreatReport threatReport = new ThreatReport();

        threatReport.setName(reportName);
        threatReport.setDate(this.getDate());

        ArrayList<ThreatCategory> threatCategoryArrayList = new ArrayList<ThreatCategory>();

        for (String threatCategoryID : threatCategoryHashMap.keySet()){

            ThreatCategory threatCategory = threatCategoryHashMap.get(threatCategoryID);

            if (threatCategory.getThreatList().size() != 0) {
                threatCategoryArrayList.add(threatCategory);
            }
        }

        threatReport.setThreatCategories(threatCategoryArrayList);

        return threatReport;
    }

    /**
     *
     *
     * @return
     */
    private String getDate(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();

        return (dateFormat.format(date));
    }
}
