package req

type ListLogsRequestSchema struct {
	PackageName string
	Page        int32
}

type ListLogsRequest struct {
	Schema        ListLogsRequestSchema
	DecodingError error
}
