package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.DatabaseService2;


/**
 * Controller for the TimeRecording-requests (Employee-View).
 * 
 * TODO: Employee- Feature Team
 */
public class EmployeeNameController extends Controller {
    
    
    /**
     * Authenticate method: Gets JSON;
     * parses employeeId and looks for corresponding name and last_name in database
     * responses with JSON {"name":"nameLastName"}
     * Tested with POSTMAN:
     * Example request: 
     * POST http://localhost:9000/login Headers Content-Type application/json Body {"employeeId":1,"pw":"thomas"} RESPONSE Body {"authenticated":"true"}
     * 
     */
    public Result getFullName() {
        JsonNode json = request().body().asJson();
        Integer employeeId = json.findPath("employeeId").intValue();
//        if(employeeId == null) {
//            return badRequest("Missing parameter [employeeId]");
//        }else{
            String fullname = DatabaseService2.queryEmployeeName(employeeId);
            ObjectNode result = Json.newObject();
            result.put("name", fullname);
            return ok(result);
//        }
    }

}

