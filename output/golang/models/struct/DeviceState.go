package main

type DeviceState struct {
	OtherThreads []ThreadInformation `json:"other_threads" bson:"other_threads"`
	CurrentThread ThreadInformation `json:"current_thread" bson:"current_thread"`
	InfoMap map[string]string `json:"info_map" bson:"info_map"`
	IsConnectedToInternet bool `json:"is_connected_to_internet" bson:"is_connected_to_internet"`
	TimeZone string `json:"time_zone" bson:"time_zone"`
	StorageInformation []DeviceStorageInformation `json:"storage_information" bson:"storage_information"`
}