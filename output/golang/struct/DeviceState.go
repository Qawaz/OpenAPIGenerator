package main

type DeviceState struct {
	other_threads []ThreadInformation
	current_thread ThreadInformation
	info_map map[string]string
	is_connected_to_internet bool
	time_zone string
	storage_information []DeviceStorageInformation
}