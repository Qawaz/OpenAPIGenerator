package api

import (
	"server/api/req"
	"server/api/res"
)

type Service interface {
	UploadLogFile(request req.UploadLogFileRequest) res.UploadLogFileResponse
	ListLogs(request req.ListLogsRequest) res.ListLogsResponse
}