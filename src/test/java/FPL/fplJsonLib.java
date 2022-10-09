package FPL;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.json.simple.JSONObject;
import org.testng.IReporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static smith.rowe.randomData.genRandomMgrId;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import objectRepo.parama;
import objectRepo.reUsableMethods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class fplJsonLib extends  parama{
   ExtentReports extent=new ExtentReports();

  // @BeforeTest
   public void config() {
      String path = System.getProperty("user.dir")+"\\reports\\index.html";
      ExtentSparkReporter reporter=new ExtentSparkReporter(path);
      reporter.config().setReportName("Web Automation Results");
      reporter.config().setDocumentTitle("Test Natije");


      extent = new ExtentReports();
      extent.attachReporter(reporter);
      extent.setSystemInfo("Tester","Richter");

   }
   @Test
    public void differdange() throws IOException {
      extent.createTest("differdange");
       int randomManagerId= genRandomMgrId();

       System.out.println(" Testing FPL  - " +randomManagerId );
       RestAssured.baseURI ="https://fantasy.premierleague.com";
       String getReqRes =
               given().
                       when().get("/api/entry/" + randomManagerId   +"/history/").
                       then().assertThat().statusCode(200).extract().response().asString();
       JsonPath js = new JsonPath(getReqRes);
       reUsableMethods sd = new reUsableMethods();
       // sd.coordsExtractor(getReqRes);
       //sd.fplJsonExtraction(getReqRes);
       sd.fplFirstSeasonsCount(getReqRes);
       sd.fplPastSeasonsDetails(getReqRes);

       extent.flush();
   }
   @Test
   public void fplAPIwithWeb() throws IOException {
      int randomManagerId= genRandomMgrId();

      System.out.println(" Testing FPL  - " +randomManagerId );
      RestAssured.baseURI ="https://fantasy.premierleague.com";
      String getReqRes =
              given().
                      when().get("/api/entry/" + randomManagerId   +"/history/").
                      then().assertThat().statusCode(200).extract().response().asString();
      JsonPath js = new JsonPath(getReqRes);
      reUsableMethods sd = new reUsableMethods();
      // sd.coordsExtractor(getReqRes);
      //sd.fplJsonExtraction(getReqRes);
      sd.fplFirstSeasonsCount(getReqRes);
      sd.fplPastSeasonsDetails(getReqRes);
      driver=initilizeDriver();
      driver.manage().window().maximize();		// maximizing the window
      int  gameweek =4 ;
      String uri= "https://fantasy.premierleague.com/entry/" + randomManagerId + "/event/"+ gameweek ;
      driver.get(uri);
      String kanda = initilizeBrowser();
      System.out.println("browser is   "+kanda);
   }

   @Test
   public void jsonMan() throws IOException {

      JSONObject obj=new JSONObject();
      obj.put("managerId","232124");
      obj.put("firstSeason","232124");
      obj.put("Seasons","232124");
      obj.put("Remark","232124");

      FileWriter file = new FileWriter("jason\\test.json",false);
      file.write(obj.toJSONString());
      file.close();

   }

   public static void CoreHava() {

       ArrayList<String> a=new ArrayList<String>();
       a.add("rahul");
       a.add("java");
       a.add("java");
       System.out.println(a);
       a.add(0, "student");
       System.out.println(a);
       /*a.remove(1); a.remove("java"); System.out.println(a);*/ System.out.println(a.get(2));
       // testing System.out.println(a.contains("java"));
       // System.out.println(a.indexOf("rahul"));
       // System.out.println(a.isEmpty()); System.out.println(a.size()); }
       // protected void abc() { // TODO Auto-generated method stub System.out.println("hello"); }
   }
    @Test
    public static void CoreHavaSet() {
        HashSet<String> a=new HashSet<String>();
        a.add("rahul");
        a.add("java");
        a.add("java");
        System.out.println(a);
        System.out.println(a.remove("javas"));
    }




}
