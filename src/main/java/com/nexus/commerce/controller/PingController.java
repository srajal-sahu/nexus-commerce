package com.nexus.commerce.controller;

import com.nexus.commerce.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ping")
@Tag(name = "System Health", description = "System ping and availability probe")
public class PingController {

    @GetMapping
    @Operation(summary = "Ping the service", description = "Returns active status of NexusCommerce backend")
    public ResponseEntity<ApiResponse<Map<String, String>>> ping() {
        return ResponseEntity.ok(ApiResponse.ok("Service is running", Map.of(
                "status", "UP",
                "service", "nexus-commerce",
                "version", "1.0.0"
        )));
    }
}