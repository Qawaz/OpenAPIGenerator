package main

type DeviceStorageInformation struct {
	Storage_name string `json:"storage_name" bson:"storage_name"`
	Total_space int `json:"total_space" bson:"total_space"`
	Free_space int `json:"free_space" bson:"free_space"`
}