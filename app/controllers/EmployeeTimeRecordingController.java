package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.DatabaseService;
import services.DatabaseService2;


/**
 * Controller for the TimeRecording-requests (Employee-View).
 * 
 * TODO: Employee- Feature Team
 */
public class EmployeeTimeRecordingController extends Controller {
    
    
    /**
     * Store task method: Gets JSON;
     * parses the necessary data to create a new Task object and stores it in the DB with a generated ID
     * responses with JSON {"successful" : true} if the storage went good or {"successful" : false} if not
     * Tested with POSTMAN:
     * Example request: 
     * POST http://localhost:9000/recording/task Headers Content-Type application/json Body {"timeSpent" : double, "date" : String, "notes" : String, "projectId" : int, "employeeId" : int} RESPONSE Body {"successful" : boolean}
     */
    public Result storeTask() {
        JsonNode json = request().body().asJson();
        String dateString = json.findPath("date").textValue();
        String notes = json.findPath("notes").textValue();
        Double timeSpent = json.findPath("timeSpent").doubleValue();
        Integer projectId = json.findPath("projectId").intValue();
        Integer employeeId = json.findPath("employeeId").intValue();

        if (timeSpent == null || dateString == null || notes == null || projectId == null || employeeId == null) {
            return badRequest("Missing parameter [time], [date], [notes], [projectId] or [employeeId]");
        } else {
            DatabaseService2.insertNewTask(timeSpent, dateString, notes, projectId, employeeId);
            ObjectNode result = Json.newObject();
            result.put("successful", true);
            return ok(result);
        }
    }

    
    /**
     * Authenticate method: Gets JSON;
     * parses employeeId and password and compares it with the pw stored in the DB
     * responses with JSON {"authenticated":"true"} if pw correct or {"authenticated":"false"} if not
     * Attention: Currently no encryption!
     * Tested with POSTMAN:
     * Example request: 
     * POST http://localhost:9000/login Headers Content-Type application/json Body {"employeeId":1,"pw":"thomas"} RESPONSE Body {"authenticated":"true"}
     * 
     */
    public Result authenticate() {
        JsonNode json = request().body().asJson();
        Integer employeeId = json.findPath("employeeId").intValue();
        String pw = json.findPath("pw").textValue();
        if(employeeId == null || pw == null) {                           //currently only checking if either id or pw is missing and sending badRequest
            return badRequest("Missing parameter [id] or [pw]");
        } else {
            String pwQuery = DatabaseService2.queryEmployeePwAndIsController(employeeId).getPw();
            boolean isController = DatabaseService2.queryEmployeePwAndIsController(employeeId).isController;
            if(pw.equals(pwQuery)) {
                ObjectNode result = Json.newObject();
                result.put("authenticated", true);
                result.put("isController", isController);
                return ok(result);
            }else {
                ObjectNode result = Json.newObject();
                result.put("authenticated", false);
                return ok(result);
            }
        }
    }
    
    /**
     * getProjects method taken from EmployeeAnalyticsController: 
     * responses in JSON with all projects included according to employeeId
     * Example request: 
     * GET http://localhost:9000/recording/1/projects
     * RESPONSE:
     * {"header":"employeeProjects","payload":{"employeeId":1,"employeeProjects":
     * [{"projectId":1,"projectName":"Build IT System","workingHours":400.0},
     * {"projectId":2,"projectName":"Blueprint","workingHours":80.0}]}}
     * 
     */
    public Result getProjects(Integer id) {
        JsonNode employeeProjects = Json.toJson(DatabaseService.queryEmployeeProjects(id));
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.put("employeeId", id);
        payload.set("employeeProjects", employeeProjects);
        result.put("header", "employeeProjects");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }
    
    private Result jsonResult(Result httpResponse) {
        httpResponse.as("application/json; charset=utf-8");
        return httpResponse;
    }

}
