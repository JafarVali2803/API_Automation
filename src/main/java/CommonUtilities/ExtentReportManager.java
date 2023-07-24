package CommonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.restassured.http.Header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExtentReportManager {

        public static ExtentReports extentReports;

        public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {

            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
            extentSparkReporter.config().setReportName(reportName);
            extentSparkReporter.config().setDocumentTitle(documentTitle);
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setEncoding("utf-8");
            extentSparkReporter.viewConfigurer().viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST}).apply();
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
            return extentReports;
        }

        public static String getReportNameWithTimeStamp() {
            DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDateTime localDateTime = LocalDateTime.now();
            String formattedTime = TimeFormatter.format(localDateTime);
            String formattedDate = dateFormatter.format(localDateTime);
            //String reportName = formattedDate+"/TestReport_" + formattedTime + ".html";
            String reportName = "Extent"+"_report_"+formattedDate+"__"+formattedTime+".html";
            return reportName;
        }

        public static void logPassDetails(String log) throws Exception{
            try{
                Listener.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
            }
            catch (Exception e){

            }

        }
        public static void logFailureDetails(String log) throws Exception{
            Listener.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
        }
        public static void logExceptionDetails(String log) throws Exception{
            Listener.extentTest.get().fail(log);
        }
        public static void logInfoDetails(String log) throws Exception{
            try {
                Listener.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
            }
            catch (Exception e){

            }
        }
        public static void logWarningDetails(String log) throws Exception{
            Listener.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
        }
        public static void logJson(String json) {
            try {
                Listener.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
            }
            catch (Exception e){

            }
        }
        public static void logHeaders(List<Header> headersList) throws Exception{

            try {
                String[][] arrayHeaders = headersList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                        .toArray(String[][] :: new);
                Listener.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
            }
            catch (Exception e){

            }

        }


    }
