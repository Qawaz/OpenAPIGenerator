package res

import "server/models"

type GenerateTokenResponse interface {
    GetGenerateTokenSchema() any
    GetStatusCode() int
}

type GenerateTokenResponse200Schema struct {
	RequestToken *string `json:"request_token" bson:"request_token"`
	EncryptionKey *string `json:"encryption_key" bson:"encryption_key"`
	ExpTime *string `json:"exp_time" bson:"exp_time"`
}

type GenerateTokenResponse200 struct {
    Schema GenerateTokenResponse200Schema
}

type GenerateTokenResponse400 struct {
    Schema models.Error
}

type GenerateTokenResponse401 struct {
    Schema models.Error
}

type GenerateTokenResponse403 struct {
    Schema models.Error
}

type GenerateTokenResponse500 struct {
    Schema models.Error
}

func (r GenerateTokenResponse200) GetGenerateTokenSchema() any {
    return r.Schema
}

func (r GenerateTokenResponse200) GetStatusCode() int {
    return 200
}

func (r GenerateTokenResponse400) GetGenerateTokenSchema() any {
    return r.Schema
}

func (r GenerateTokenResponse400) GetStatusCode() int {
    return 400
}

func (r GenerateTokenResponse401) GetGenerateTokenSchema() any {
    return r.Schema
}

func (r GenerateTokenResponse401) GetStatusCode() int {
    return 401
}

func (r GenerateTokenResponse403) GetGenerateTokenSchema() any {
    return r.Schema
}

func (r GenerateTokenResponse403) GetStatusCode() int {
    return 403
}

func (r GenerateTokenResponse500) GetGenerateTokenSchema() any {
    return r.Schema
}

func (r GenerateTokenResponse500) GetStatusCode() int {
    return 500
}