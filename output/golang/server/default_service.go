package main

import (
    "server/api/req"
    "server/api/res"
)

type DefaultService struct {
}


func (service DefaultService) UploadLogFile(request req.UploadLogFileRequest) res.UploadLogFileResponse {
    // TODO not done yet
    return nil
}

func (service DefaultService) ListLogs(request req.ListLogsRequest) res.ListLogsResponse {
    // TODO not done yet
    return nil
}
