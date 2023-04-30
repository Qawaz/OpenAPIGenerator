package res

import "server/models"

type UploadLogFileResponse interface {
    GetUploadLogFileSchema() any
}

type UploadLogFileResponse200 struct {
    Schema models.Success
}

type UploadLogFileResponse400 struct {
    Schema models.Error
}

type UploadLogFileResponse401 struct {
    Schema models.Error
}

type UploadLogFileResponse500 struct {
    Schema models.Error
}

func (r UploadLogFileResponse200) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse400) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse401) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse500) GetUploadLogFileSchema() any {
    return r.Schema
}