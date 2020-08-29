package com.bychis.auth_tool.api.controller;

import com.bychis.auth_tool.api.request_holders.BadgeEvaluationRequestHolder;
import com.bychis.auth_tool.api.request_holders.RoleEvaluationRequestHolder;
import com.bychis.auth_tool.rules.AccessRequest;
import com.bychis.auth_tool.rules.Decision;
import com.bychis.auth_tool.rules.Value;
import com.bychis.auth_tool.service.AccessRequestService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/access_request")
public class AccessRequestController {
    private final AccessRequestService accessRequestService;

    public AccessRequestController(AccessRequestService accessRequestService) {
        this.accessRequestService = accessRequestService;
    }

    @RequestMapping( value = "/badge_evaluation", method = RequestMethod.GET )
    public Decision badgeEvaluationRequest(@RequestBody BadgeEvaluationRequestHolder
                                                       request,
                                           HttpServletRequest servletRequest) {
        AccessRequest accessRequest = new AccessRequest("badge_evaluation",
                request.getBid());
        accessRequest.setIp(servletRequest.getRemoteAddr());
        return accessRequestService.readBadgeEvaluationDecision(accessRequest);
    }

    @GetMapping( path = "/role_evaluation" )
    public Decision roleEvaluationRequest(@RequestBody RoleEvaluationRequestHolder
                                                      request) {
        AccessRequest accessRequest = new AccessRequest("role_evaluation",
                request.getRequestID(),
                request.getUserID());
        return accessRequestService.readRoleEvaluationDecision(accessRequest);
    }

    @PostMapping( path = "/profile_evaluation" )
    public Decision profileEvaluationRequest(@RequestBody String requestID,
                                             @RequestBody List<Value> valueList) {
        AccessRequest accessRequest = new AccessRequest(requestID,valueList);
        Decision decision = accessRequestService.readProfileEvaluationDecision(accessRequest);
        return decision;
    }

    @PostMapping( path = "/environment_evaluation" )
    public Decision EnvironmentEvaluationRequest(@RequestBody String requestID,
                                                 @RequestBody List<Value> valueList) {
        AccessRequest accessRequest = new AccessRequest(requestID,valueList);
        Decision decision = accessRequestService.readContextEvaluationDecision(accessRequest);
        return decision;
    }
}
