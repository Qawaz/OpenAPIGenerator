package main

type DeviceStorageInformation struct {
	StorageName string `json:"storage_name" bson:"storage_name"`
	TotalSpace int `json:"total_space" bson:"total_space"`
	FreeSpace int `json:"free_space" bson:"free_space"`
}