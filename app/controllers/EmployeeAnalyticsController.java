package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.*;
import services.DatabaseService;

/**
 * Controller for the Analytics-View of one Employee.
 *
 */
public class EmployeeAnalyticsController extends Controller {

    public Result getWeeklyWorkingHours(Integer id) {
        JsonNode weeks = Json.toJson(DatabaseService.queryWeeklyWorkingHours(id));
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.put("employeeId", id);
        payload.set("weeklyWorkingHours", weeks);
        result.put("header", "weeklyWorkingHours");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getMonthlyWorkingHours(Integer id) {
        JsonNode months = Json.toJson(DatabaseService.queryMonthlyWorkingHours(id));
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.put("employeeId", id);
        payload.set("monthlyWorkingHours", months);
        result.put("header", "monthlyWorkingHours");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

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

    public Result getProjectBudgets(Integer id) {
        JsonNode employeeProjects = Json.toJson(DatabaseService.queryEmployeeProjectBudget(id));
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.put("employeeId", id);
        payload.set("employeeProjectBudgets", employeeProjects);
        result.put("header", "employeeProjectBudgets");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getOverallWorkingHours(Integer id) {
        JsonNode employeeWorkingHours = Json.toJson(DatabaseService.queryEmployeeWorkingHours(id));
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.put("employeeId", id);
        payload.set("employeeWorkingHours", employeeWorkingHours);
        result.put("header", "employeeWorkingHours");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    private Result jsonResult(Result httpResponse) {
        httpResponse.as("application/json; charset=utf-8");
        return httpResponse;
    }

}
