package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.DatabaseService;

/**
 * Controller for the Analytics-View of the Mgmt.
 * 
 */
public class MgmtAnalyticsController extends Controller {

    public Result getProjects() {
        JsonNode hoursPerProject = Json.toJson(DatabaseService.queryHoursPerProject());
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.set("hoursPerProject", hoursPerProject);
        result.put("header", "hoursPerProject");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getProjectBudgets() {
        JsonNode projectBudgets = Json.toJson(DatabaseService.queryProjectBudgets());
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.set("projectBudgets", projectBudgets);
        result.put("header", "projectBudgets");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getProjectStaffing() {
        JsonNode projectStaffing = Json.toJson(DatabaseService.queryProjectStaffing());
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.set("projectStaffing", projectStaffing);
        result.put("header", "projectStaffing");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getWeeklyHoursPerProject() {
        JsonNode weeklyHoursPerProject = Json.toJson(DatabaseService.queryWeeklyHoursPerProject());
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.set("weeklyHoursPerProject", weeklyHoursPerProject);
        result.put("header", "weeklyHoursPerProject");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    public Result getProjectDuration() {
        JsonNode projectDuration = Json.toJson(DatabaseService.queryProjectDuration());
        ObjectNode result = Json.newObject();
        ObjectNode payload = Json.newObject();
        payload.set("projectDuration", projectDuration);
        result.put("header", "projectDuration");
        result.set("payload", payload);
        return jsonResult(ok(result));
    }

    private Result jsonResult(Result httpResponse) {
        httpResponse.as("application/json; charset=utf-8");
        return httpResponse;
    }
}
