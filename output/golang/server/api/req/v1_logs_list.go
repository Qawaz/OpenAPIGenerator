package req

type ListLogsRequestSchema struct {
	PackageName string `json:"package_name" bson:"package_name"`
	Page int `json:"page" bson:"page"`
}

type ListLogsRequest struct {
	Schema        ListLogsRequestSchema
	BearerToken   string
	DecodingError error
}