package api

import (
	"server/api/req"
	"server/api/res"
)

type Service interface {
	GetApiStatus() res.GetApiStatusResponse
	GenerateToken() res.GenerateTokenResponse
	UploadLogFile(request req.UploadLogFileRequest) res.UploadLogFileResponse
	ListLogs(request req.ListLogsRequest) res.ListLogsResponse
}