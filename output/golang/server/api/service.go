package api

import (
	"server/api/req"
	"server/api/res"
)

type Service interface {
	ListLogs(request req.ListLogsRequest) res.ListLogsResponse
	UploadLogsFile(request req.UploadLogFileRequest) res.UploadLogsFileResponse
}
