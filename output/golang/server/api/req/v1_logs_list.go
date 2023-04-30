package req

import "net/http"

type ListLogsRequestSchema struct {
	PackageName string `json:"package_name" bson:"package_name"`
	Page int `json:"page" bson:"page"`
}

type ListLogsRequest struct {
	Schema        ListLogsRequestSchema
    Writer        http.ResponseWriter
    Request       *http.Request
	BearerToken   string
	DecodingError error
}