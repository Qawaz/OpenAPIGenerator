package req

type ListLogsRequestSchema struct {
	PackageName string `json:"package_name"`
	Page        int32  `json:"page"`
}

type ListLogsRequest struct {
	Schema        ListLogsRequestSchema
	BearerToken   string
	DecodingError error
}
