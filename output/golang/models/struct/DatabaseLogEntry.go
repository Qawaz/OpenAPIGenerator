package main

type DatabaseLogEntry struct {
	Notes []string `json:"notes" bson:"notes"`
	Log []LogEntry `json:"log" bson:"log"`
	CreatedAt *int `json:"created_at" bson:"created_at"`
	Id *string `json:"id" bson:"id"`
	State string `json:"state" bson:"state"`
	Device DeviceInformation `json:"device" bson:"device"`
	AppInformation AppInformation `json:"app_information" bson:"app_information"`
	UserInformation UserInformation `json:"user_information" bson:"user_information"`
}