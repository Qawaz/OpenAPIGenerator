package main

type DeviceState struct {
	Other_threads []ThreadInformation `json:"other_threads" bson:"other_threads"`
	Current_thread ThreadInformation `json:"current_thread" bson:"current_thread"`
	Info_map map[string]string `json:"info_map" bson:"info_map"`
	Is_connected_to_internet bool `json:"is_connected_to_internet" bson:"is_connected_to_internet"`
	Time_zone string `json:"time_zone" bson:"time_zone"`
	Storage_information []DeviceStorageInformation `json:"storage_information" bson:"storage_information"`
}