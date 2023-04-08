package main

type info_map struct {

}

type DeviceState struct {
	other_threads []ThreadInformation
	info_map Info_map
	is_connected_to_internet bool
	time_zone string
	storage_information []DeviceStorageInformation
}