package api

import (
    "net/http"
)

type Route struct {
    Name        string
    Method      string
    Pattern     string
    HandlerFunc http.HandlerFunc
}

type Routes []Route

// CreateRoutes returns all the api routes for the DefaultApiController
func CreateRoutes(c DefaultApiRouter) *Routes {
    return &Routes{
        {
            "getApiStatus",
            "GET",
            "/status",
            c.GetApiStatus,
        },
        {
            "generateToken",
            "POST",
            "/v1/token",
            c.GenerateToken,
        },
        {
            "uploadLogFile",
            "POST",
            "/v1/logs/upload",
            c.UploadLogFile,
        },
        {
            "listLogs",
            "POST",
            "/v1/logs/list",
            c.ListLogs,
        },
    }
}