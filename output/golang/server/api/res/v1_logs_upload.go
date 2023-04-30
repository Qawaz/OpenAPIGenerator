package res

import "server/models"

type UploadLogFileResponse interface {
    GetUploadLogFileSchema() any
    GetStatusCode() int
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

func (r UploadLogFileResponse200) GetStatusCode() int {
    return 200
}

func (r UploadLogFileResponse400) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse400) GetStatusCode() int {
    return 400
}

func (r UploadLogFileResponse401) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse401) GetStatusCode() int {
    return 401
}

func (r UploadLogFileResponse500) GetUploadLogFileSchema() any {
    return r.Schema
}

func (r UploadLogFileResponse500) GetStatusCode() int {
    return 500
}